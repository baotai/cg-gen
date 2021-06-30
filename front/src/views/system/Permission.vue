<template>
  <div class="app-container">

    <el-input v-model="keyword" placeholder="请输入关键字搜索" size="mini" clearable style="width: 280px;margin-left: 18px" @input="initPage" />
    <el-button type="primary" size="mini" icon="el-icon-plus" style="margin-bottom: 10px;margin-left: 18px" @click="onPermissionAdd">新增权限</el-button>

    <div>
      <el-card v-for="(permission, i) in PermissionList" :key="i" :index="permission.name" class="text item">
        <div slot="header">
          <el-button type="text" size="mini" @click="onPermissionUpdate(permission)"> {{ permission.desc }} ( {{ permission.name }} )</el-button>
          <el-button style="float: right; padding: 3px 0" type="text" @click="onPermissionDelete(permission)"><i class="el-icon-delete" /></el-button>
        </div>
        <div @click="onPermissionUpdate(permission)">
          <el-button type="text" size="mini"> {{ permission.url }} </el-button>
        </div>
      </el-card>
    </div>

    <el-dialog :title="title" :visible.sync="dialogFormVisible" @close="clear">
      <el-form ref="dataForm" v-model="form" style="text-align: left">
        <el-form-item label="权限名称" :label-width="formLabelWidth" prop="title">
          <el-input v-model="form.name" autocomplete="off" placeholder="请输入权限名称" />
        </el-form-item>
        <el-form-item label="描述" :label-width="formLabelWidth" prop="title">
          <el-input v-model="form.desc" autocomplete="off" placeholder="请输入描述" />
        </el-form-item>
        <el-form-item label="URI" :label-width="formLabelWidth" prop="title">
          <el-input v-model="form.url" autocomplete="off" placeholder="请输入URI" />
        </el-form-item>
        <el-form-item prop="id" style="height: 0">
          <el-input v-model="form.id" type="hidden" autocomplete="off" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="onSubmit">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'Permission',
  data() {
    return {
      keyword: '',
      PermissionList: [],
      form: {
        'id': 0,
        'name': '',
        'desc': '',
        'url': ''
      },
      dialogFormVisible: false,
      formLabelWidth: '120px',
      title: '角色',
      created: true
    }
  },
  mounted() {
    this.initPage()
  },
  methods: {
    initPage() {
      this.findByKeyword(this.keyword)
    },
    findByKeyword: function(keyword) {
      this.get(`/system/permission/findByKeyword?keyword=${keyword}&currentPage=1&pageSize=200`, {}, function(resp) {
        this.PermissionList = resp.data.rows
      })
    },
    onSubmit() {
      const data = {
        'id': this.form.id,
        'name': this.form.name,
        'desc': this.form.desc,
        'url': this.form.url
      }
      if (this.created) {
        this.post(`/system/permission/save`, data, function(resp) {
          this.initPage()
          this.dialogFormVisible = false
        })
      } else {
        this.post(`/system/permission/updateById`, data, function(resp) {
          this.initPage()
          this.dialogFormVisible = false
        })
      }
    },
    clear() {
      this.form = {
        'id': 0,
        'name': '',
        'desc': '',
        'url': ''
      }
    },
    onPermissionAdd() {
      Object.keys(this.form).forEach(key => { this.form[key] = '' })
      this.form.id = 0
      this.dialogFormVisible = true
      this.created = true
      this.title = '新建权限'
    },
    onPermissionUpdate(permission) {
      Object.assign(this.form, permission)
      this.dialogFormVisible = true
      this.created = false
      this.title = '更新权限'
    },
    onPermissionDelete(permission) {
      this.confirm(`确认要删除 ${permission.name} 吗？`, function(done) {
        const data = {
          'id': permission.id,
          'name': permission.name,
          'desc': permission.desc,
          'url': permission.url
        }
        this.post('/system/permission/deleteById', data, function() {
          done()
          location.reload()
        })
      })
    }
  }
}
</script>

<style scoped>
  .text {
    font-size: 14px;
  }
  .item {
    padding: 5px 0;
  }
  .el-card {
    width: 250px;
    float: left;
    margin-left: 25px;
    margin-top: 25px;
  }
  i {
    float: right;
    padding: 3px 0;
    color: red;
  }
</style>
