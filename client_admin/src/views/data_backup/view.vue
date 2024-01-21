<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','backup_time') || $check_field('add','backup_time') || $check_field('set','backup_time')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="备份时间" prop="backup_time">
					<el-date-picker :disabled="disabledObj['backup_time_isDisabled']" v-if="user_group === '管理员' || (form['data_backup_id'] && $check_field('set','backup_time')) || (!form['data_backup_id'] && $check_field('add','backup_time'))" id="backup_time"
						v-model="form['backup_time']" type="date" placeholder="选择日期">
					</el-date-picker>
					<div v-else-if="$check_field('get','backup_time')">{{form['backup_time']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','backup_name') || $check_field('add','backup_name') || $check_field('set','backup_name')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="备份名称" prop="backup_name">
					<el-input id="backup_name" v-model="form['backup_name']" placeholder="请输入备份名称"
							  v-if="user_group === '管理员' || (form['data_backup_id'] && $check_field('set','backup_name')) || (!form['data_backup_id'] && $check_field('add','backup_name'))" :disabled="disabledObj['backup_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','backup_name')">{{form['backup_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','backup_files') || $check_field('add','backup_files') || $check_field('set','backup_files')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="备份文件" prop="backup_files">
					<div v-if="disabledObj['backup_files_isDisabled']">
						<div v-if="$check_field('get','backup_files')">
							<el-button type="primary" @click="$download($fullUrl(form['backup_files']),form['backup_files'])">下载<i
									class="el-icon-download el-icon--right"></i></el-button>
						</div>
					</div>
					<div v-else>
						<el-upload v-if="user_group === '管理员' || (form['data_backup_id'] && $check_field('set','backup_files')) || (!form['data_backup_id'] && $check_field('add','backup_files'))" class="upload-demo" drag
								   action="" style="max-width: 300px;width: 100%;" :http-request="upload_backup_files" :limit="1" accept="">
							<i class="el-icon-upload"></i>
							<div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
						</el-upload>
						<div v-else-if="$check_field('get','backup_files')">
							<el-button type="primary" @click="$download($fullUrl(form['backup_files']),form['backup_files'])">下载<i
									class="el-icon-download el-icon--right"></i></el-button>
						</div>
					</div>
				</el-form-item>
			</el-col>
			<el-col :xs="24" :sm="12" :lg="8" class="el_form_btn_warp">
				<el-form-item>
					<el-button type="primary" @click="submit()">提交</el-button>
					<el-button @click="cancel()">取消</el-button>
				</el-form-item>
			</el-col>

		</el-form>
	</el-main>
</template>

<script>
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		data() {
			return {
				field: "data_backup_id",
				url_add: "~/api/data_backup/add?",
				url_set: "~/api/data_backup/set?",
				url_get_obj: "~/api/data_backup/get_obj?",
				url_upload: "~/api/data_backup/upload?",

				query: {
					"data_backup_id": 0,
				},

				form: {
					"backup_time":'', // 备份时间
					"backup_name":'', // 备份名称
					"backup_files":'', // 备份文件
					"data_backup_id": 0, // ID

				},
				disabledObj:{
					"backup_time_isDisabled": false,
					"backup_name_isDisabled": false,
					"backup_files_isDisabled": false,
				},
			}
		},
		methods: {
			/**
			 * 上传备份文件
			 * @param {Object} param文件参数
			 */
			upload_backup_files(param){
				this.uploadFile(param.file, "backup_files");
			},

			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
				if(this.form && form){
					Object.keys(this.form).forEach(key => {
						Object.keys(form).forEach(dbKey => {
							// if(dbKey === "charging_standard"){
							// 	this.form['charging_rules'] = form[dbKey];
							// 	this.disabledObj['charging_rules_isDisabled'] = true;
							// };
							if(key === dbKey){
								this.disabledObj[key+'_isDisabled'] = true;
							}
						})
					})
				}
        if (this.form["backup_time"].indexOf("-")===-1){
          this.form["backup_time"] = this.$toTime(parseInt(this.form["backup_time"]),"yyyy-MM-dd")
        }
				$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){
				if(parseInt(this.form["backup_time"]) > 9999){
					this.form["backup_time"] = this.$toTime(parseInt(this.form["backup_time"]),"yyyy-MM-dd")
				}
			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/data_backup/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/data_backup/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/data_backup/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/data_backup/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/data_backup/view','get');
					console.log(bl ? "你有视图查询权限视作有查询权限" : "你没有视图查询权限");
				}

				console.log(bl ? "具有当前页面的查看权，请注意这不代表你有字段的查看权" : "无权查看当前页，请注意即便有字段查询权限没有页面查询权限也不行");

				return bl;
			},
			/**
			 * 上传文件
			 * @param {Object} param
			 */
			uploadimg(param) {
				this.uploadFile(param.file, "avatar");
			},

		},
		created() {
		},
	}
</script>

<style>
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
