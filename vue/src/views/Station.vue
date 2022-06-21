<template>
  <div>

    <div style="padding: 10px 0">
      <el-input style="width: 200px" placeholder="Please enter station id" suffix-icon="el-icon-search"
                v-model="id"></el-input>
      <el-input style="width: 200px" placeholder="Please enter station name" suffix-icon="el-icon-message"
                v-model="name" class="ml-5"></el-input>
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
          title="Are you sure to remove the station selected?"
          @confirm="delBatch"
      >
        <el-button type="danger" slot="reference">Delete<i class="el-icon-remove-outline"></i></el-button>
      </el-popconfirm>

    </div>

    <el-table :data="tableData" border stripe header-cell-class-name="'headerBg'"
              @selection-change="handleSelectChange"
              ref="filterTable">
      <el-table-column
          type="selection"
          width="50">
      </el-table-column>
      <el-table-column prop="id" label="ID">
      </el-table-column>
      <el-table-column prop="name" label="Name">
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
              title="Are you sure to remove this station?"
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

    <el-dialog title="Station's information" :visible.sync="dialogFormVisible" width="30%">
      <el-form label-width="80px">
        <el-form-item label="Station name">
          <el-input v-model="form.name" autocomplete="off"></el-input>
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
import {serverIp, serverPort} from "../../public/config";


export default {
  name: "Station",
  data() {
    return {
      serverIp: serverIp,
      totalData: [],
      total: 0,
      pageNum: 0,
      pageSize: 10,

      name: "",
      id: "",
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
      this.request.get("/station/page", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          username: this.username,
          id:this.id,
          name:this.name
        }
      }).then(res => {
        this.tableData = res.data.records
        this.total = res.data.total
      })
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
      this.name = "";
      this.id="";
      this.load();
    },
    handleAdd() {
      this.dialogFormVisible = true
      this.form = {}
    },
    save() {
      this.request.post("/station", this.form).then(res => {

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
      this.request.delete("/station/" + id).then(res => {
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
      this.request.post("/station/del/batch", ids).then(res => {
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
    handleExcelImportSuccess() {
      this.$message.success("Successfully import!")
      this.load()
    }
  }
}
</script>

<style scoped>

</style>