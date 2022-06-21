<template>
  <div>
    <div style="padding: 10px 0">
      <el-input style="width: 200px" placeholder="Please enter filename" suffix-icon="el-icon-search"
                v-model="name">
      </el-input>
      <el-button class="ml-5" type="primary" @click="load">Search</el-button>
      <el-button class="ml-5" type="primary" @click="reset">Reset</el-button>

    </div>
    <div style="padding: 10px 0">
      <el-upload
          :action="'http://' + serverIp + ':'+serverPort+'/file/upload'" style="display: inline-block" :show-file-list="false"
          :on-success="handleFileUploadSuccess"
      >
        <el-button type="primary" class="ml-5">Upload<i class="el-icon-top"></i></el-button>
      </el-upload>
      <el-popconfirm
          class="ml-5"
          confirm-button-text='Yes'
          cancel-button-text='No'
          icon="el-icon-info"
          icon-color="red"
          title="Are you sure to remove the files selected?"
          @confirm="delBatch"
      >
        <el-button type="danger" slot="reference">Delete<i class="el-icon-remove-outline"></i></el-button>
      </el-popconfirm>


    </div>
    <el-table :data="tableData" border stripe header-cell-class-name="'headerBg'"
              @selection-change="handleSelectChange">
      <el-table-column
          type="selection"
          width="55">
      </el-table-column>
      <el-table-column prop="id" label="ID">
      </el-table-column>
      <el-table-column prop="name" label="Name">
      </el-table-column>
      <el-table-column prop="url" label="URL">
      </el-table-column>
      <el-table-column prop="userId" label="User ID">
      </el-table-column>
      <el-table-column prop="size" label="Size">
      </el-table-column>
<!--      <el-table-column label="Enable">-->
<!--        <template slot-scope="scope">-->
<!--          <el-switch v-model="scope.row.enable" active-color="#13ce66" inactive-color="#ccc"-->
<!--                     @change="changeEnable(scope.row)"></el-switch>-->
<!--        </template>-->
<!--      </el-table-column>-->

      <el-table-column label="Operation" width="200" align="center">
        <template slot-scope="scope">
          <el-button type="primary" @click="download(scope.row.url)">Download</el-button>
          <el-popconfirm
              class="ml-5"
              confirm-button-text='Yes'
              cancel-button-text='No'
              icon="el-icon-info"
              icon-color="red"
              title="Are you sure to remove this user?"
              @confirm="del(scope.row.id)"
          >
            <el-button type="danger" slot="reference">Delete<i class="el-icon-remove-outline"></i></el-button>
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
  </div>
</template>

<script>
import {serverIp, serverPort} from "../../public/config";

export default {
  name: "File",
  data() {
    return {
      serverIp: serverIp,
      serverPort:serverPort,
      tableData: [],
      name: '',
      multipleSection: [],
      pageNum: 1,
      pageSize: 10,
      total: 0,
    }
  },
  created() {
    this.load();
  },
  methods: {
    load() {
      this.request.get("/file/page", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          name: this.name,
        }
      }).then(res => {
        console.log(res)
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
      this.load();
    },

    del(id) {
      this.request.delete("/file/" + id).then(res => {
        if (res.code === '200') {
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
      this.request.post("/file/del/batch", ids).then(res => {
        if (res.code === '200') {
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

    handleFileUploadSuccess() {
      this.$message.success("Successfully upload!")
      this.load()
    },
    download(url) {
      window.open('http://'+serverIp+':'+serverPort+'/file/'+url)
    },
    // changeEnable(row) {
    //   this.request.post("/file/update", row).then(res => {
    //     if (res.code === '200') {
    //       this.$message.success("Successfully update!")
    //     }
    //   })
    // }
  }
}
</script>

<style scoped>

</style>