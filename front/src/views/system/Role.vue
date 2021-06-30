<template>
  <div class="app-container">

    <el-input v-model="keyword" placeholder="请输入关键字搜索" size="mini" clearable style="width: 280px;margin-left: 18px" @input="initPage" />
    <el-button type="primary" size="mini" icon="el-icon-plus" style="margin-bottom: 10px;margin-left: 18px" @click="onRoleAdd">新增角色</el-button>

    <div>
      <el-card v-for="(role, i) in roleList" :key="i" :index="role.roleName" class="text item" style="width: 250px;float: left;margin-left: 25px;margin-top: 25px;">
        <div slot="header">
          <el-button type="text" size="mini" @click="onRoleUpdate(role)"> {{ role.desc }} ( {{ role.roleName }} )</el-button>
          <el-button style="float: right; padding: 3px 0" type="text" @click="onRoleDelete(role)"><i class="el-icon-delete" /></el-button>
        </div>
        <div>
          <el-link type="info" style="float: left">菜单分配</el-link>
          <el-button type="text" size="mini" style="float: right; margin-right: 3px" @click="onRolePermission(role)">权限分配</el-button>
        </div>
      </el-card>
    </div>

    <el-dialog :title="title" :visible.sync="dialogFormVisible" @close="clear">
      <el-form ref="dataForm" v-model="form" style="text-align: left">
        <el-form-item label="角色名称" :label-width="formLabelWidth" prop="title">
          <el-input v-model="form.roleName" autocomplete="off" placeholder="请输入角色名称" />
        </el-form-item>
        <el-form-item label="描述" :label-width="formLabelWidth" prop="title">
          <el-input v-model="form.desc" autocomplete="off" placeholder="请输入描述" />
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

    <el-dialog :title="rolePermissionForm.title" :visible.sync="dialogRolePermissionFormVisible" @open="getRolePermissions" @close="clearRolePermission">
      <el-card class="box-card">
        <div class="block">
          <el-checkbox-group v-model="rolePermissionForm.permissions">
            <el-checkbox v-for="(permission, i) in permissionList" :key="i" :label="permission.id" :index="permission.name"> {{ permission.desc }} </el-checkbox>
          </el-checkbox-group>
        </div>
      </el-card>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogRolePermissionFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="onRolePermissionSubmit">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'Role',
  data() {
    return {
      keyword: '',
      roleList: [],
      permissionList: [],
      form: {
        'id': 0,
        'roleName': '',
        'desc': ''
      },
      rolePermissionForm: {
        title: '分配权限',
        roleId: 0,
        permissions: []
      },
      dialogFormVisible: false,
      dialogRolePermissionFormVisible: false,
      formLabelWidth: '120px',
      title: '角色',
      created: true
    }
  },
  mounted() {
    this.initPage()
    this.getPermissions()
  },
  methods: {
    initPage() {
      this.findByKeyword(this.keyword)
    },
    findByKeyword: function(keyword) {
      this.get(`/system/role/findByKeyword?keyword=${keyword}&currentPage=1&pageSize=200`, {}, function(resp) {
        this.roleList = resp.data.rows
      })
    },
    getPermissions: function() {
      this.get(`/system/permission/findByKeyword?keyword=&currentPage=1&pageSize=500`, {}, function(resp) {
        this.permissionList = resp.data.rows
      })
    },
    getRolePermissions: function() {
      this.get(`/system/role_permission/findByRoleId?roleId=${this.rolePermissionForm.roleId}`, {}, function(resp) {
        this.rolePermissionForm.permissions = resp.data
      })
    },
    onRolePermissionSubmit() {
      this.post(`/system/role_permission/update`, {
        'roleId': this.rolePermissionForm.roleId,
        'permissionIds': this.rolePermissionForm.permissions
      }, function(resp) {
        this.initPage()
        this.dialogRolePermissionFormVisible = false
      })
    },
    onSubmit() {
      if (this.created) {
        this.post(`/system/role/save`, {
          'id': this.form.id,
          'roleName': this.form.roleName,
          'desc': this.form.desc
        }, function(resp) {
          this.initPage()
          this.dialogFormVisible = false
        })
      } else {
        this.post(`/system/role/updateById`, {
          'id': this.form.id,
          'roleName': this.form.roleName,
          'desc': this.form.desc
        }, function(resp) {
          this.initPage()
          this.dialogFormVisible = false
        })
      }
    },
    clear() {
      this.form = {
        'id': 0,
        'roleName': '',
        'desc': ''
      }
    },
    clearRolePermission() {
      this.rolePermissionForm = {
        title: '分配权限',
        roleId: 0,
        permissions: []
      }
    },
    onRolePermission(role) {
      this.dialogRolePermissionFormVisible = true
      this.rolePermissionForm.title = '分配权限' + '(' + role.roleName + ')'
      this.rolePermissionForm.roleId = role.id
    },
    onRoleAdd() {
      Object.keys(this.form).forEach(key => { this.form[key] = '' })
      this.form.id = 0
      this.dialogFormVisible = true
      this.created = true
      this.title = '新建角色'
    },
    onRoleUpdate(role) {
      Object.assign(this.form, role)
      this.dialogFormVisible = true
      this.created = false
      this.title = '更新角色'
    },
    onRoleDelete(role) {
      this.confirm(`确认要删除 ${role.roleName} 吗？`, function(done) {
        const data = {
          'id': role.id,
          'roleName': role.roleName,
          'desc': role.desc
        }
        this.post('/system/role/deleteById', data, function() {
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
  i {
    float: right;
    padding: 3px 0;
    color: red;
  }
</style>
