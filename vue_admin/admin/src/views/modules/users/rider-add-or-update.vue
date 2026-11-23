<template>
  <div class="main-content" :style='{"padding":"0 20px 20px 20px","background":"none","flexWrap":"wrap","height":"100%","flexDirection":"row"}'>
    <el-form :model="form" :rules="rules" ref="form" label-position="left" label-width="100px" :style='{"width":"100%","order":"1","flex":"1 0 auto","display":"block"}'>
      <el-form-item label="用户名" prop="username">
        <el-input v-model="form.username" placeholder="请输入用户名" :disabled="!isEdit"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input v-model="form.password" type="password" placeholder="请输入密码" :disabled="!isEdit"></el-input>
      </el-form-item>
      <el-form-item label="姓名" prop="name">
        <el-input v-model="form.name" placeholder="请输入姓名"></el-input>
      </el-form-item>
      <el-form-item label="手机号" prop="phone">
        <el-input v-model="form.phone" placeholder="请输入手机号"></el-input>
      </el-form-item>
      <el-form-item label="身份证号" prop="idCard">
        <el-input v-model="form.idCard" placeholder="请输入身份证号" :disabled="!isEdit"></el-input>
      </el-form-item>
      <el-form-item label="头像">
        <el-upload
          class="upload-demo"
          action="/api/file/upload"
          :on-success="handleAvatarSuccess"
          :before-upload="beforeAvatarUpload"
          :show-file-list="false"
        >
          <img v-if="form.avatar" :src="form.avatar" class="avatar">
          <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>
      </el-form-item>
      <el-form-item label="状态" prop="status" v-if="!isInfo">
        <el-switch v-model="form.status" active-color="#13ce66" inactive-color="#ff4949"></el-switch>
      </el-form-item>
      <el-form-item v-if="!isInfo">
        <el-button type="primary" @click="submitForm" :loading="submitLoading">提交</el-button>
        <el-button @click="goBack">取消</el-button>
      </el-form-item>
      <el-form-item v-else>
        <el-button @click="goBack">返回</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      form: {
        id: '',
        username: '',
        password: '',
        name: '',
        phone: '',
        idCard: '',
        avatar: '',
        status: 0
      },
      submitLoading: false,
      isEdit: false,
      isInfo: false,
      rules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' }
        ],
        name: [
          { required: true, message: '请输入姓名', trigger: 'blur' }
        ],
        phone: [
          { required: true, message: '请输入手机号', trigger: 'blur' }
        ],
        idCard: [
          { required: true, message: '请输入身份证号', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    init(id, type) {
      this.submitLoading = false;
      this.isInfo = type === 'info';
      this.isEdit = !id;
      
      if (id) {
        this.$http({
          url: '/api/rider/info/' + id,
          method: 'get',
          
        }).then(({ data }) => {
          if (data && data.code === 0) {
            this.form = data.data;
          }
        });
      } else {
        this.form = {
          id: '',
          username: '',
          password: '',
          name: '',
          phone: '',
          idCard: '',
          avatar: '',
          status: 0
        };
      }
    },
    submitForm() {
      this.$refs.form.validate((valid) => {
        if (valid) {
          this.submitLoading = true;
          let url = this.form.id ? '/api/rider/update' : '/api/rider/save';
          this.$http({
            url: url,
            method: 'post',
            data: this.form
          }).then(({ data }) => {
            this.submitLoading = false;
            if (data && data.code === 0) {
              this.$message({
                message: '操作成功',
                type: 'success',
                duration: 1500,
                onClose: () => {
                  this.goBack();
                }
              });
            } else {
              this.$message.error(data.msg);
            }
          });
        }
      });
    },
    goBack() {
      this.$emit('goBack');
      this.$parent.showFlag = true;
      this.$parent.addOrUpdateFlag = false;
      this.$parent.getDataList();
    },
    handleAvatarSuccess(res, file) {
      if (res.code === 0) {
        this.form.avatar = res.file;
      } else {
        this.$message.error('上传失败');
      }
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg' || file.type === 'image/png';
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error('只能上传JPG/PNG图片!');
      }
      if (!isLt2M) {
        this.$message.error('上传图片大小不能超过2MB!');
      }
      return isJPG && isLt2M;
    }
  }
};
</script>

<style scoped>
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>
