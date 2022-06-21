<template>
  <div>

    <div style="padding: 10px 0">
      <el-input style="width: 200px" placeholder="Please enter name" suffix-icon="el-icon-search"
                v-model="name"></el-input>
      <el-input style="width: 200px" placeholder="Please enter account" suffix-icon="el-icon-message"
                v-model="account" class="ml-5"></el-input>
      <el-input style="width: 200px" placeholder="Please enter phone" suffix-icon="el-icon-position"
                v-model="phone" class="ml-5"></el-input>
      <el-input style="width: 200px" placeholder="Please enter health position" suffix-icon="el-icon-position"
                v-model="health" class="ml-5"></el-input>
      <el-button class="ml-5" type="primary" @click="load">search</el-button>
      <el-button class="ml-5" type="primary" @click="reset">reset</el-button>
    </div>

    <div style="padding: 10px 0">
      <el-button type="primary" @click="handleAdd">Add<i class="el-icon-circle-plus-outline"></i></el-button>

      <el-popconfirm
          class="ml-5"
          confirm-button-text='Yes'
          cancel-button-text='No'
          icon="el-icon-info"
          icon-color="red"
          title="Are you sure to remove the users selected?"
          @confirm="delBatch"
      >
        <el-button type="danger" slot="reference">Delete<i class="el-icon-remove-outline"></i></el-button>
      </el-popconfirm>


      <el-upload
          :action="'http://' + serverIp + '9090/user/import'" style="display: inline-block" :show-file-list="false"
          :accept="xlsx" :on-success="handleExcelImportSuccess"
      >
        <el-button type="primary" class="ml-5">Import<i class="el-icon-bottom"></i></el-button>
      </el-upload>
      <el-button type="primary" @click="exp" class="ml-5">Export<i class="el-icon-top"></i></el-button>
    </div>

    <el-table :data="tableData" border stripe header-cell-class-name="'headerBg'"
              @selection-change="handleSelectChange"
              ref="filterTable">
      <el-table-column
          type="selection"
          width="50">
      </el-table-column>
      <el-table-column prop="id" label="ID" width="140">
      </el-table-column>
      <el-table-column prop="name" label="Name" width="120">
      </el-table-column>
      <el-table-column prop="health" label="Health">
      </el-table-column>
      <el-table-column prop="balance" label="Balance">
      </el-table-column>
      <el-table-column prop="account" label="Account">
      </el-table-column>
      <el-table-column prop="phone" label="Phone">
      </el-table-column>


      <el-table-column
          prop="role"
          label="Role"
          :filters="[{ text: 'User', value: '1' },{ text: 'Admin', value: '2' }, { text: 'Boss', value: '3' }]"
          :filter-method="filterTag"
          filter-placement="bottom-end">
        <template slot-scope="scope">
          <el-tag
              :type="scope.row.role == '1' ? 'primary' : 'success'"
              disable-transitions>{{roles[scope.row.role-1].role}}</el-tag>
        </template>
      </el-table-column>

      <el-table-column width="300" align="center" label="Operation">
        <template slot-scope="scope">
          <el-button type="success" @click="handleEdit(scope.row)">Edit<i class="el-icon-edit"></i></el-button>
          <el-popconfirm
              class="ml-5"
              confirm-button-text='Yes'
              cancel-button-text='No'
              icon="el-icon-info"
              icon-color="red"
              title="Are you sure to remove this user?"
              @confirm="del(scope.row.id)"
          >
            <el-button type="danger" slot="reference">Remove<i class="el-icon-remove-outline"></i></el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <div style="padding: 10px 0">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageNum"
          :page-sizes="[2,5,10,15]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </div>

    <el-dialog title="User's information" :visible.sync="dialogFormVisible" width="30%">
      <el-form label-width="80px">
        <el-form-item label="Username">
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="Role">
          <el-select clearable v-model="form.role" placeholder="Please select role" style="width: 80%">
            <el-option v-for="item in roles" :key="item.role" :label="item.role" :value="item.id"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="Account">
          <el-input v-model="form.account" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="Phone">
          <el-input v-model="form.phone" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="ID Card">
          <el-input v-model="form.idcard" autocomplete="off"></el-input>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">Cancel</el-button>
        <el-button type="primary" @click="save">Save</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import {serverIp} from "../../public/config";


export default {
  name: "User",
  data() {
    return {
      serverIp: serverIp,
      totalData: [],
      total: 0,
      pageNum: 0,
      pageSize: 10,

      name: "",
      health: "",
      balance: 0,
      idcard: "",
      role: -1,
      account: '',
      phone: '',

      form: {},
      tableData: [],
      dialogFormVisible: false,
      multipleSection: [],
      roles: []
    }
  },
  created() {
    this.load();
  },
  methods: {
    load() {
      this.request.get("/user/page", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          username: this.username,
          email: this.email,
          address: this.address
        }
      }).then(res => {
        this.tableData = res.data.records
        this.total = res.data.total
      })

      this.request.get("/role").then(res => {
        this.roles = res.data
        console.log(this.roles)
      })
    },
    filterTag(value, row) {
      console.log(value)
      console.log(row.role)
      return row.role == value;
    },
    handleSizeChange(pageSize) {
      this.pageSize = pageSize;
      this.load();
    },
    handleCurrentChange(pageNum) {
      this.pageNum = pageNum;
      this.load();
    },
    reset() {
      // this.email = "";

      this.load();
    },
    handleAdd() {
      this.dialogFormVisible = true
      this.form = {}
    },
    save() {
      this.request.post("/user", this.form).then(res => {

        if (res.code === '200') {
          this.$message.success("Save successfully!")
          this.dialogFormVisible = false
          this.load()
        } else {
          this.$message.error("Fail to add!")
        }
      })
    },
    handleEdit(row) {
      this.form = JSON.parse(JSON.stringify(row))
      this.dialogFormVisible = true
    },
    del(id) {
      this.request.delete("/user/" + id).then(res => {
        if (res) {
          this.$message.success("Delete successfully!")
          this.load()
        } else {
          this.$message.error("Fail to delete!")
        }
      })
    },
    delBatch() {
      let ids = this.multipleSection.map(v => v.id)
      console.log(ids)
      this.request.post("/user/del/batch", ids).then(res => {
        if (res) {
          this.$message.success("Delete successfully!")
          this.load()
        } else {
          this.$message.error("Fail to delete!")
        }
      })
    },
    handleSelectChange(val) {
      this.multipleSection = val
    },
    exp() {
      window.open(`http://${serverIp}:9090/user/export`)
    },
    handleExcelImportSuccess() {
      this.$message.success("Successfully import!")
      this.load()
    }
  }
}
</script>

<style scoped>

</style>