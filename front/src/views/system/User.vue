<template>
  <div class="app-container">

    <el-input v-model="keyword" placeholder="请输入关键字搜索" size="mini" clearable style="width: 280px;margin-left: 18px" @input="initPage" />
    <el-button type="primary" size="mini" icon="el-icon-plus" style="margin-bottom: 10px;margin-left: 18px" @click="onUserAdd">新增用户</el-button>
    <el-table :data="userList" border highlight-current-row style="width: 98%;margin: 18px 18px">
      <el-table-column prop="username" label="用户名" width="280px" />
      <el-table-column prop="mobile" label="联系方式" width="140px" />
      <el-table-column prop="email" label="邮箱" width="380px" />
      <el-table-column prop="cgModifiedBy" label="修改人" width="280px" />
      <el-table-column prop="cgModified" label="修改时间" :formatter="dateFormat" width="180px" />
      <el-table-column label="操作" width="167px">
        <template slot-scope="scope">
          <el-button type="text" size="mini" @click="onUserUpdate(scope)">修改</el-button>
          <el-button type="text" size="mini" @click="onUserDelete(scope.row)">删除</el-button>
          <el-button type="text" size="mini" @click="onUserRole(scope.row)">角色分配</el-button>
        </template>
      </el-table-column>
    </el-table>

    <div class="block" style="text-align: right;margin-right: 35px;margin-top: 35px">
      <el-pagination
        layout="total, sizes, prev, pager, next, jumper"
        :current-page="currentPage"
        :page-sizes="[10, 15, 30, 60]"
        :page-size="pageSize"
        :page-count="total"
        :total="records"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
      />
    </div>

    <el-dialog :title="title" :visible.sync="dialogFormVisible" @close="clear">
      <el-form ref="dataForm" v-model="form" style="text-align: left">
        <el-form-item label="用户名" :label-width="formLabelWidth" prop="title">
          <el-input v-model="form.username" autocomplete="off" placeholder="请输入用户名" />
        </el-form-item>
        <el-form-item v-show="created" label="密码" :label-width="formLabelWidth" prop="author">
          <el-input v-model="form.password" type="password" autocomplete="off" placeholder="请输入密码" />
        </el-form-item>
        <el-form-item label="联系方式" :label-width="formLabelWidth" prop="title">
          <el-input v-model="form.mobile" autocomplete="off" placeholder="请输入联系方式" />
        </el-form-item>
        <el-form-item label="邮箱" :label-width="formLabelWidth" prop="title">
          <el-input v-model="form.email" autocomplete="off" placeholder="请输入邮箱" />
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

    <el-dialog :title="userRoleForm.title" :visible.sync="dialogUserRoleFormVisible" @open="getUserRoles" @close="clearUserRole">
      <el-card class="box-card">
        <div class="block">
          <el-checkbox-group v-model="userRoleForm.roles">
            <el-checkbox v-for="(role, i) in roleList" :key="i" :label="role.id" :index="role.roleName"> {{ role.desc }} </el-checkbox>
          </el-checkbox-group>
        </div>
      </el-card>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogUserRoleFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="onUserRoleSubmit">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'User',
  data() {
    return {
      keyword: '',
      currentPage: 1,
      pageSize: 10,
      records: 0,
      total: 0,
      userList: [],
      roleList: [],
      form: {
        'id': 0,
        'username': '',
        'password': '',
        'mobile': '',
        'email': ''
      },
      userRoleForm: {
        title: '分配角色',
        userId: 0,
        roles: []
      },
      dialogFormVisible: false,
      dialogUserRoleFormVisible: false,
      formLabelWidth: '120px',
      title: '用户',
      created: true
    }
  },
  mounted() {
    this.initPage()
    this.getRoles()
  },
  methods: {
    initPage() {
      this.findByKeyword(this.keyword, this.currentPage)
    },
    findByKeyword: function(keyword, currentPage) {
      this.get(`/system/user/findByKeyword?keyword=${keyword}&currentPage=${currentPage}&pageSize=${this.pageSize}`, {}, function(resp) {
        this.userList = resp.data.rows
        this.records = resp.data.records
        this.total = resp.data.total
      })
    },
    getRoles: function() {
      this.get(`/system/role/findByKeyword?keyword=&currentPage=1&pageSize=500`, {}, function(resp) {
        this.roleList = resp.data.rows
      })
    },
    getUserRoles: function() {
      this.get(`/system/user_role/findByUserId?userId=${this.userRoleForm.userId}`, {}, function(resp) {
        this.userRoleForm.roles = resp.data
      })
    },
    dateFormat: function(data) {
      return this.$moment(data.cgModified).format('YYYY-MM-DD HH:mm:ss')
    },
    handleSizeChange(val) {
      this.pageSize = val
      this.initPage()
    },
    handleCurrentChange(val) {
      this.currentPage = val
      this.initPage()
    },
    clear() {
      this.form = {
        'id': 0,
        'username': '',
        'password': '',
        'mobile': '',
        'email': ''
      }
    },
    clearUserRole() {
      this.userRoleForm = {
        title: '分配角色',
        userId: 0,
        roles: []
      }
    },
    onUserRoleSubmit() {
      this.post(`/system/user_role/update`, {
        'userId': this.userRoleForm.userId,
        'roleIds': this.userRoleForm.roles
      }, function(resp) {
        this.initPage()
        this.dialogUserRoleFormVisible = false
      })
    },
    onSubmit() {
      if (this.created) {
        this.post(`/system/user/save`, {
          'id': this.form.id,
          'username': this.form.username,
          'password': this.form.password,
          'mobile': this.form.mobile,
          'email': this.form.email
        }, function(resp) {
          this.initPage()
          this.dialogFormVisible = false
        })
      } else {
        this.post(`/system/user/updateById`, {
          'id': this.form.id,
          'username': this.form.username,
          'password': this.form.password,
          'mobile': this.form.mobile,
          'email': this.form.email
        }, function(resp) {
          this.initPage()
          this.dialogFormVisible = false
        })
      }
    },
    onUserAdd() {
      Object.keys(this.form).forEach(key => { this.form[key] = '' })
      this.form.id = 0
      this.dialogFormVisible = true
      this.created = true
      this.title = '新建用户'
    },
    onUserUpdate(scope) {
      Object.assign(this.form, scope.row)
      this.dialogFormVisible = true
      this.created = false
      this.title = '更新用户'
    },
    onUserRole(user) {
      this.dialogUserRoleFormVisible = true
      this.userRoleForm.title = '分配角色' + '(' + user.username + ')'
      this.userRoleForm.userId = user.id
    },
    onUserDelete(user) {
      this.confirm(`确认要删除 ${user.username} 吗？`, function(done) {
        const data = {
          'id': user.id,
          'username': user.username,
          'password': user.password,
          'mobile': user.mobile,
          'email': user.email
        }
        this.post('/system/user/deleteById', data, function() {
          done()
          location.reload()
        })
      })
    }
  }
}
</script>

<style scoped>

</style>
