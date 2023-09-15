<template>
  <div style="padding: 10px">
    <!--    功能区域-->
    <div style="margin: 10px 0">
      <el-button type="primary" @click="add">新增</el-button>
      <el-button type="primary">导入</el-button>
      <el-button type="primary">导出</el-button>
    </div>
    <!--    搜索区域-->
    <div style="margin: 10px 0">
      <el-input v-model="search" placeholder="请输入数据" style="width: 20%" clearable/>
      <el-button type="primary" style="margin-left: 5px" @click="load">查询</el-button>
    </div>
    <el-table :data="tableData" border stripe style="width: 100%">
      <el-table-column prop="id" label="ID" sortable/>
      <el-table-column prop="name" label="名称"/>
      <el-table-column prop="price" label="单价"/>
      <el-table-column prop="author" label="作者"/>
      <el-table-column prop="createTime" label="出版"/>
      <el-table-column label="封面" >
        <template #default="scope">
          <el-image style="width: 100px;height: 100px" :src="scope.row.cover"
                    :preview-src-list="[scope.row.cover]"
          ></el-image>
        </template>
      </el-table-column>
      <el-table-column fixed="right" label="操作" width="150">
        <template #default="scope">
          <el-button link size="mini" @click="handleEdit(scope.row)">编辑</el-button>
          <el-popconfirm title="确认删除吗?" @confirm="handleDelete(scope.row.id)">
            <template #reference>
              <el-button size="mini" type="danger" >删除</el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog title="提示" v-model="dialogVisible" width="40%">
      <el-form :model="form" label-width="120px">
        <el-form-item label="名称">
          <el-input v-model="form.name" style="width: 80%"></el-input>
        </el-form-item>
        <el-form-item label="价格">
          <el-input v-model="form.price" style="width: 80%"></el-input>
        </el-form-item>
        <el-form-item label="作者">
          <el-input v-model="form.author" style="width: 80%"></el-input>
        </el-form-item>
        <el-form-item label="出版时间">
          <el-date-picker v-model="form.createTime" value-format="YYYY-MM-DD" type="date" style="width: 80%" clearable></el-date-picker>
        </el-form-item>
        <el-form-item label="封面">
          <el-upload ref="upload" action="http://localhost:9090/files/upload" :on-success="filesUploadSuccess">
            <el-button type="primary">点击上传</el-button>
          </el-upload>
        </el-form-item>
      </el-form>
      <template #footer>
          <span class="dialog-footer">
            <el-button @click="dialogVisible = false">取消</el-button>
            <el-button type="primary" @click="save">确定</el-button>
          </span>
      </template>
    </el-dialog>

    <div style="margin: 10px 0">
      <el-pagination
          v-model:current-page="currentPage"
          :page-size="pageSize"
          :page-sizes="[5, 10, 20]"
          layout="total, sizes, prev, pager, next, jumper"
          :total=total
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
      />
    </div>
  </div>
</template>

<script>
// @ is an alias to /src
import Avatar from 'vue-avatar'
import request from "../utils/request";  //必须加这个
export default {
  name: 'Book',
  components: {

  },
  data(){

    return{
      form: {},
      dialogVisible:false,

      search:'',
      currentPage:1,
      pageSize:10,
      total:0,
      tableData:[

      ]
    }
  },
  created() {
    this.load()
  },        //调用load方法
  methods:{
    filesUploadSuccess(res){
      this.form.cover=res.data
    },
    load() {
      request.get("/book", {
        params: {
          pageNum: this.currentPage,
          pageSize: this.pageSize,
          search: this.search
        }
      }).then(res => {
        console.log(res)
        this.tableData = res.data.records
        this.total = res.data.total
      })
    },
    add(){
      this.dialogVisible=true
      this.form={}
      if(this.$refs['upload']){
        this.$refs['upload'].clearFiles()
      }
    },//如果表里有id那么更新
    save(){
      if (this.form.id) { //更新
        request.put("/book", this.form).then(res => {
          console.log(res)
          if (res.code === '0') {
            this.$message({
              type: "success",
              message: "更新成功"
            })
            this.load()  //刷新表格的数据
            this.dialogVisible = false
          } else {
            this.$message({
              type: "error",
              message: res.msg
            })
          }

        })
      } else { //新增
        request.post("/book", this.form).then(res => {
          console.log(res)
          if (res.code === '0') {
            this.$message({
              type: "success",
              message: "新增成功"
            })
          } else {
            this.$message({
              type: "error",
              message: res.msg
            })
          }
          this.load()  //刷新表格的数据
          this.dialogVisible = false

        })
      }
    },
    handleEdit(row){
      //点击编辑弹窗弹出，把数据填到弹窗里，深拷贝避免编辑完点取消表格里数据变化
      this.form = JSON.parse(JSON.stringify(row))
      this.dialogVisible = true
      this.$nextTick(()=>{
        this.$refs['upload'].clearFiles() //清除上传图片的历史记录
      })
    },
    handleDelete(id){
      request.delete("/book/" + id).then(res => {
        if (res.code === '0') {
          this.$message({
            type: "success",
            message: "删除成功"
          })
        } else {
          this.$message({
            type: "error",
            message: res.msg
          })
        }
        this.load()   //重新加载表格数据
        // this.dialogVisible = false
      })

    },
    handleSizeChange(pageSzie){
      this.pageSize=pageSzie
      this.load()
    },
    handleCurrentChange(pageNum){
      this.pageNum=pageNum
      this.load()
    }
  }
}
</script>
