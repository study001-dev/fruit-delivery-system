<template>
<view class="content">
	<view class="box" :style='{"minHeight":"100vh","width":"100%","padding":"100rpx 0 500rpx","background":"url(http://codegen.caihongy.cn/20230307/094c3ad2a7b2458cb232f725b0df6c3c.png) no-repeat center top / 100% auto,url(http://codegen.caihongy.cn/20230307/8849983a5e0d4d478e58af3273722fb2.png) no-repeat center bottom / 100% auto,#c8d9eb","height":"100%"}'>
		<view :style='{"padding":"220rpx 40rpx 200rpx","boxShadow":"0px 0px 0px #ddd","borderColor":"#eee","margin":"0 auto","borderRadius":"40rpx","flexWrap":"wrap","background":"url(http://codegen.caihongy.cn/20230307/f3271c90048c489eac6e1dad3e5f7b8f.png) no-repeat center top / 100% auto,url(http://codegen.caihongy.cn/20230307/b55ee0c1c78748be9a3ed379b82c35f7.png) no-repeat center bottom / 100% auto,url(http://codegen.caihongy.cn/20230307/0edf45915c52473a89294f6620bb5eb9.png) repeat-y center center / 100% auto","borderWidth":"0px","display":"flex","width":"80%","borderStyle":"solid","height":"auto"}'>
			<image :style='{"width":"160rpx","margin":"0 auto 24rpx auto","borderRadius":"40rpx","display":"none","height":"160rpx"}' src="http://codegen.caihongy.cn/20201114/7856ba26477849ea828f481fa2773a95.jpg" mode="aspectFill"></image>
			
			<!-- 用户注册表单 -->
			<template v-if="tableName=='yonghu'">
				<view :style='{"width":"100%","margin":"0 0 40rpx 0","height":"auto"}' class="uni-form-item uni-column">
					<input :style='{"padding":"0px 24rpx","boxShadow":"0px 0px 0px #2c77cb","margin":"0px","borderColor":"#ddd","color":"#333","borderRadius":"40rpx","background":"rgba(255,255,255,.8)","borderWidth":"2rpx","width":"100%","fontSize":"28rpx","borderStyle":"solid","height":"80rpx"}'  v-model="ruleForm.yonghuming"  type="text"  class="uni-input" name="" placeholder="用户名" />
				</view>
				<view :style='{"width":"100%","margin":"0 0 40rpx 0","height":"auto"}' class="uni-form-item uni-column">
					<input :style='{"padding":"0px 24rpx","boxShadow":"0px 0px 0px #2c77cb","margin":"0px","borderColor":"#ddd","color":"#333","borderRadius":"40rpx","background":"rgba(255,255,255,.8)","borderWidth":"2rpx","width":"100%","fontSize":"28rpx","borderStyle":"solid","height":"80rpx"}'  v-model="ruleForm.xingming"  type="text"  class="uni-input" name="" placeholder="姓名" />
				</view>
				<view :style='{"width":"100%","margin":"0 0 40rpx 0","height":"auto"}' @tap="yonghutouxiangTap" class="">
					<view>请上传头像</view>
					<image :style='{"border":"0px solid #ccc","minHeight":"120rpx","borderRadius":"40rpx","objectFit":"cover","display":"block","width":"200rpx","height":"auto"}' v-if="ruleForm.touxiang" class="avator" :src="baseUrl+ruleForm.touxiang" mode=""></image>
					<image :style='{"border":"0px solid #ccc","minHeight":"120rpx","borderRadius":"40rpx","objectFit":"cover","display":"block","width":"200rpx","height":"auto"}' v-else class="avator" src="../../static/gen/upload.png" mode=""></image>
				</view>
				<view :style='{"width":"100%","margin":"0 0 40rpx 0","height":"auto"}' class="uni-form-item uni-column">
					<input :style='{"padding":"0px 24rpx","boxShadow":"0px 0px 0px #2c77cb","margin":"0px","borderColor":"#ddd","color":"#333","borderRadius":"40rpx","background":"rgba(255,255,255,.8)","borderWidth":"2rpx","width":"100%","fontSize":"28rpx","borderStyle":"solid","height":"80rpx"}'  v-model="ruleForm.mima" type="password"  class="uni-input" name="" placeholder="密码" />
				</view>
				<view :style='{"width":"100%","margin":"0 0 40rpx 0","height":"auto"}' class="uni-form-item uni-column">
					<input :style='{"padding":"0px 24rpx","boxShadow":"0px 0px 0px #2c77cb","margin":"0px","borderColor":"#ddd","color":"#333","borderRadius":"40rpx","background":"rgba(255,255,255,.8)","borderWidth":"2rpx","width":"100%","fontSize":"28rpx","borderStyle":"solid","height":"80rpx"}' v-model="ruleForm.mima2" type="password" class="uni-input" name="" placeholder="确认密码" />
				</view>
				<picker :style='{"boxShadow":"0px 0px 0px #2c77cb","margin":"0","borderColor":"#ddd","borderRadius":"40rpx","background":"rgba(255,255,255,.8)","borderWidth":"2rpx","width":"100%","borderStyle":"solid","height":"auto"}'  @change="yonghuxingbieChange" :value="yonghuxingbieIndex" :range="yonghuxingbieOptions">
					<view :style='{"width":"100%","padding":"0 24rpx","lineHeight":"80rpx","fontSize":"28rpx","color":"#767676"}' class="uni-input">{{ruleForm.xingbie?ruleForm.xingbie:"请选择性别"}}</view>
				</picker>
				<view :style='{"width":"100%","margin":"0 0 40rpx 0","height":"auto"}' class="uni-form-item uni-column">
					<input :style='{"padding":"0px 24rpx","boxShadow":"0px 0px 0px #2c77cb","margin":"0px","borderColor":"#ddd","color":"#333","borderRadius":"40rpx","background":"rgba(255,255,255,.8)","borderWidth":"2rpx","width":"100%","fontSize":"28rpx","borderStyle":"solid","height":"80rpx"}'  v-model="ruleForm.shouji"  type="text"  class="uni-input" name="" placeholder="手机" />
				</view>
				<view :style='{"width":"100%","margin":"0 0 40rpx 0","height":"auto"}' class="uni-form-item uni-column">
					<input :style='{"padding":"0px 24rpx","boxShadow":"0px 0px 0px #2c77cb","margin":"0px","borderColor":"#ddd","color":"#333","borderRadius":"40rpx","background":"rgba(255,255,255,.8)","borderWidth":"2rpx","width":"100%","fontSize":"28rpx","borderStyle":"solid","height":"80rpx"}'  v-model="ruleForm.pquestion"  type="text"  class="uni-input" name="" placeholder="密保问题" />
				</view>
				<view :style='{"width":"100%","margin":"0 0 40rpx 0","height":"auto"}' class="uni-form-item uni-column">
					<input :style='{"padding":"0px 24rpx","boxShadow":"0px 0px 0px #2c77cb","margin":"0px","borderColor":"#ddd","color":"#333","borderRadius":"40rpx","background":"rgba(255,255,255,.8)","borderWidth":"2rpx","width":"100%","fontSize":"28rpx","borderStyle":"solid","height":"80rpx"}'  v-model="ruleForm.panswer"  type="text"  class="uni-input" name="" placeholder="密保答案" />
				</view>
			</template>
			
			<!-- 骑手注册表单 -->
			<template v-if="tableName=='rider'">
				<view :style='{"width":"100%","margin":"0 0 40rpx 0","height":"auto"}' class="uni-form-item uni-column">
					<input :style='{"padding":"0px 24rpx","boxShadow":"0px 0px 0px #2c77cb","margin":"0px","borderColor":"#ddd","color":"#333","borderRadius":"40rpx","background":"rgba(255,255,255,.8)","borderWidth":"2rpx","width":"100%","fontSize":"28rpx","borderStyle":"solid","height":"80rpx"}'  v-model="riderForm.username"  type="text"  class="uni-input" name="" placeholder="用户名" />
				</view>
				<view :style='{"width":"100%","margin":"0 0 40rpx 0","height":"auto"}' class="uni-form-item uni-column">
					<input :style='{"padding":"0px 24rpx","boxShadow":"0px 0px 0px #2c77cb","margin":"0px","borderColor":"#ddd","color":"#333","borderRadius":"40rpx","background":"rgba(255,255,255,.8)","borderWidth":"2rpx","width":"100%","fontSize":"28rpx","borderStyle":"solid","height":"80rpx"}'  v-model="riderForm.password" type="password"  class="uni-input" name="" placeholder="密码" />
				</view>
				<view :style='{"width":"100%","margin":"0 0 40rpx 0","height":"auto"}' class="uni-form-item uni-column">
					<input :style='{"padding":"0px 24rpx","boxShadow":"0px 0px 0px #2c77cb","margin":"0px","borderColor":"#ddd","color":"#333","borderRadius":"40rpx","background":"rgba(255,255,255,.8)","borderWidth":"2rpx","width":"100%","fontSize":"28rpx","borderStyle":"solid","height":"80rpx"}' v-model="riderForm.password2" type="password" class="uni-input" name="" placeholder="确认密码" />
				</view>
				<view :style='{"width":"100%","margin":"0 0 40rpx 0","height":"auto"}' class="uni-form-item uni-column">
					<input :style='{"padding":"0px 24rpx","boxShadow":"0px 0px 0px #2c77cb","margin":"0px","borderColor":"#ddd","color":"#333","borderRadius":"40rpx","background":"rgba(255,255,255,.8)","borderWidth":"2rpx","width":"100%","fontSize":"28rpx","borderStyle":"solid","height":"80rpx"}'  v-model="riderForm.name"  type="text"  class="uni-input" name="" placeholder="姓名" />
				</view>
				<view :style='{"width":"100%","margin":"0 0 40rpx 0","height":"auto"}' class="uni-form-item uni-column">
					<input :style='{"padding":"0px 24rpx","boxShadow":"0px 0px 0px #2c77cb","margin":"0px","borderColor":"#ddd","color":"#333","borderRadius":"40rpx","background":"rgba(255,255,255,.8)","borderWidth":"2rpx","width":"100%","fontSize":"28rpx","borderStyle":"solid","height":"80rpx"}'  v-model="riderForm.phone"  type="number"  class="uni-input" name="" placeholder="手机号" />
				</view>
				<view :style='{"width":"100%","margin":"0 0 40rpx 0","height":"auto"}' class="uni-form-item uni-column">
					<input :style='{"padding":"0px 24rpx","boxShadow":"0px 0px 0px #2c77cb","margin":"0px","borderColor":"#ddd","color":"#333","borderRadius":"40rpx","background":"rgba(255,255,255,.8)","borderWidth":"2rpx","width":"100%","fontSize":"28rpx","borderStyle":"solid","height":"80rpx"}'  v-model="riderForm.idCard"  type="text"  class="uni-input" name="" placeholder="身份证号" />
				</view>
				<view :style='{"width":"100%","margin":"20rpx 0","padding":"16rpx","background":"rgba(255,248,220,.8)","borderRadius":"10rpx","fontSize":"24rpx","color":"#8B4513"}'>
					注册后需要等待管理员审核通过才能登录
				</view>
			</template>
			
			<button :style='{"padding":"0px","boxShadow":"0px 6rpx 12rpx -10rpx #333","margin":"40rpx auto 24rpx","borderColor":"#6dd5cb","color":"#333","display":"block","borderRadius":"40rpx","background":"linear-gradient(270deg, rgba(249,236,236,1) 0%, rgba(240,217,218,1) 26%, rgba(200,217,235,1) 60%, rgba(236,242,249,1) 100%),#c8d9eb","borderWidth":"0px","width":"100%","lineHeight":"88rpx","fontSize":"32rpx","borderStyle":"solid","height":"88rpx"}' class="btn-submit" @tap="register" type="primary">注册</button>
		</view>
	</view>
</view>
</template>

<script>
    import multipleSelect from "@/components/momo-multipleSelect/momo-multipleSelect";
	export default {
		data() {
			return {
                yonghuxingbieOptions: [],
                yonghuxingbieIndex: 0,
				ruleForm: {
                yonghuming: '',
                xingming: '',
                touxiang: '',
                mima: '',
                xingbie: '',
                shouji: '',
                money: '',
                pquestion: '',
                panswer: '',
				},
				riderForm: {
					username: '',
					password: '',
					password2: '',
					name: '',
					phone: '',
					idCard: ''
				},
				tableName:""
			}
		},
        components: {
            multipleSelect
        },
        computed: {
            baseUrl() {
                return this.$base.url;
            }
        },
		async onLoad() {
			let res = [];
			let table = uni.getStorageSync("loginTable");
            this.tableName = table;

                        // 自定义下拉框值
			if(this.tableName=='yonghu'){
                this.yonghuxingbieOptions = "男,女".split(',');
				this.ruleForm.xingbie=this.yonghuxingbieOptions[0]
			}
			
			this.styleChange()
		},
		methods: {

            yonghutouxiangTap() {
                let _this = this;
                this.$api.upload(function(res) {
                    _this.ruleForm.touxiang = 'upload/' + res.file;
                    _this.$forceUpdate();
                });
            },
            // 下拉变化
            yonghuxingbieChange(e) {
                    this.yonghuxingbieIndex = e.target.value
                    this.ruleForm.xingbie = this.yonghuxingbieOptions[this.yonghuxingbieIndex]
            },
            toggleTab(str) {
                this.$refs[str].show();
            },

			styleChange() {
				this.$nextTick(()=>{
					// document.querySelectorAll('.uni-input .uni-input-input').forEach(el=>{
					//   el.style.backgroundColor = this.registerFrom.content.input.backgroundColor
					// })
				})
			},
			// 获取uuid
			getUUID () {
				return new Date().getTime();
			},
			// 注册
			async register() {
				if(this.tableName === 'yonghu') {
					// 用户注册逻辑
					if((!this.ruleForm.yonghuming)){
						this.$utils.msg(`用户名不能为空`);
						return
					}
					if((!this.ruleForm.xingming)){
						this.$utils.msg(`姓名不能为空`);
						return
					}
					if((!this.ruleForm.mima)){
						this.$utils.msg(`密码不能为空`);
						return
					}
					if((this.ruleForm.mima!=this.ruleForm.mima2)){
						this.$utils.msg(`两次密码输入不一致`);
						return
					}
					if(this.ruleForm.shouji&&(!this.$validate.isMobile(this.ruleForm.shouji))){
						this.$utils.msg(`手机应输入手机格式`);
						return
					}
					if(this.ruleForm.money&&(!this.$validate.isNumber(this.ruleForm.money))){
						this.$utils.msg(`余额应输入数字`);
						return
					}
					await this.$api.register('yonghu', this.ruleForm);
					this.$utils.msgBack('注册成功');
				} else if(this.tableName === 'rider') {
					// 骑手注册逻辑
					if(!this.riderForm.username){
						this.$utils.msg('请输入用户名');
						return
					}
					if(!this.riderForm.password){
						this.$utils.msg('请输入密码');
						return
					}
					if(this.riderForm.password != this.riderForm.password2){
						this.$utils.msg('两次密码输入不一致');
						return
					}
					if(!this.riderForm.name){
						this.$utils.msg('请输入姓名');
						return
					}
					if(!this.riderForm.phone){
						this.$utils.msg('请输入手机号');
						return
					}
					if(!this.riderForm.idCard){
						this.$utils.msg('请输入身份证号');
						return
					}
					try {
						let res = await this.$api.riderRegister(this.riderForm);
						if(res && res.code === 0) {
							this.$utils.msgBack('注册成功，请等待管理员审核');
						} else {
							this.$utils.msg(res && res.msg ? res.msg : '注册失败');
						}
					} catch (error) {
						this.$utils.msg('注册失败，请检查网络连接');
					}
				}
			}
		}
	}
</script>

<style lang="scss" scoped>
	.content {
		min-height: calc(100vh - 44px);
		box-sizing: border-box;
	}
</style>