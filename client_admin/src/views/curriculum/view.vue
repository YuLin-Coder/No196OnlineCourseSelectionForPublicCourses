<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','course_number') || $check_field('add','course_number') || $check_field('set','course_number')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="课程编号" prop="course_number">
					<el-input id="course_number" v-model="form['course_number']" placeholder="请输入课程编号"
							  v-if="user_group === '管理员' || (form['curriculum_id'] && $check_field('set','course_number')) || (!form['curriculum_id'] && $check_field('add','course_number'))" :disabled="disabledObj['course_number_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','course_number')">{{form['course_number']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','course_name') || $check_field('add','course_name') || $check_field('set','course_name')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="课程名称" prop="course_name">
					<el-input id="course_name" v-model="form['course_name']" placeholder="请输入课程名称"
							  v-if="user_group === '管理员' || (form['curriculum_id'] && $check_field('set','course_name')) || (!form['curriculum_id'] && $check_field('add','course_name'))" :disabled="disabledObj['course_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','course_name')">{{form['course_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','professional_type') || $check_field('add','professional_type') || $check_field('set','professional_type')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="专业类型" prop="professional_type">
					<el-select id="professional_type" v-model="form['professional_type']"
						v-if="user_group === '管理员' || (form['curriculum_id'] && $check_field('set','professional_type')) || (!form['curriculum_id'] && $check_field('add','professional_type'))">
						<el-option v-for="o in list_professional_type" :key="o['professional_type']" :label="o['professional_type']"
							:value="o['professional_type']">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','professional_type')">{{form['professional_type']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','course_cover') || $check_field('add','course_cover') || $check_field('set','course_cover')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="课程封面" prop="course_cover">
					<el-upload :disabled="disabledObj['course_cover_isDisabled']" id="course_cover" class="avatar-uploader" drag
						accept="image/gif, image/jpeg, image/png, image/jpg" action="" :http-request="upload_course_cover"
						:show-file-list="false" v-if="user_group === '管理员' || (form['curriculum_id'] && $check_field('set','course_cover')) || (!form['curriculum_id'] && $check_field('add','course_cover'))">
						<img v-if="form['course_cover']" :src="$fullUrl(form['course_cover'])" class="avatar">
						<i v-else class="el-icon-plus avatar-uploader-icon"></i>
					</el-upload>
					<el-image v-else-if="$check_field('get','course_cover')" style="width: 100px; height: 100px"
						:src="$fullUrl(form['course_cover'])" :preview-src-list="[$fullUrl(form['course_cover'])]">
						<div slot="error" class="image-slot">
							<img src="../../../public/img/error.png" style="width: 90px; height: 90px" />
						</div>
					</el-image>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','suitable_grade') || $check_field('add','suitable_grade') || $check_field('set','suitable_grade')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="适学年级" prop="suitable_grade">
					<el-input id="suitable_grade" v-model="form['suitable_grade']" placeholder="请输入适学年级"
							  v-if="user_group === '管理员' || (form['curriculum_id'] && $check_field('set','suitable_grade')) || (!form['curriculum_id'] && $check_field('add','suitable_grade'))" :disabled="disabledObj['suitable_grade_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','suitable_grade')">{{form['suitable_grade']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','sk_address') || $check_field('add','sk_address') || $check_field('set','sk_address')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="上课地点" prop="sk_address">
					<el-input id="sk_address" v-model="form['sk_address']" placeholder="请输入上课地点"
							  v-if="user_group === '管理员' || (form['curriculum_id'] && $check_field('set','sk_address')) || (!form['curriculum_id'] && $check_field('add','sk_address'))" :disabled="disabledObj['sk_address_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','sk_address')">{{form['sk_address']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','sk_time') || $check_field('add','sk_time') || $check_field('set','sk_time')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="上课时间" prop="sk_time">
					<el-date-picker :disabled="disabledObj['sk_time_isDisabled']" v-if="user_group === '管理员' || (form['curriculum_id'] && $check_field('set','sk_time')) || (!form['curriculum_id'] && $check_field('add','sk_time'))" id="sk_time"
						v-model="form['sk_time']" type="datetime" placeholder="选择日期">
					</el-date-picker>
					<div v-else-if="$check_field('get','sk_time')">{{form['sk_time']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','xueshi') || $check_field('add','xueshi') || $check_field('set','xueshi')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="学时" prop="xueshi">
					<el-input id="xueshi" v-model="form['xueshi']" placeholder="请输入学时"
							  v-if="user_group === '管理员' || (form['curriculum_id'] && $check_field('set','xueshi')) || (!form['curriculum_id'] && $check_field('add','xueshi'))" :disabled="disabledObj['xueshi_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','xueshi')">{{form['xueshi']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','score') || $check_field('add','score') || $check_field('set','score')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="学分" prop="score">
					<el-input id="score" v-model="form['score']" placeholder="请输入学分"
							  v-if="user_group === '管理员' || (form['curriculum_id'] && $check_field('set','score')) || (!form['curriculum_id'] && $check_field('add','score'))" :disabled="disabledObj['score_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','score')">{{form['score']}}</div>
				</el-form-item>
			</el-col>
			
			<el-col v-if="user_group === '管理员' || $check_field('get','teacher') || $check_field('add','teacher') || $check_field('set','teacher')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="教师" prop="teacher">
						<div v-if="user_group !== '管理员'">
							{{ get_user_session_teacher(form['teacher']) }}
							<!--<el-input id="business_name" v-model="form['teacher']" placeholder="请输入教师"-->
							<!--v-if="user_group === '管理员' || (form['curriculum_id'] && $check_field('set','teacher')) || (!form['curriculum_id'] && $check_field('add','teacher'))" :disabled="disabledObj['teacher_isDisabled']"></el-input>-->
							<!--<div v-else-if="$check_field('get','teacher')">{{form['teacher']}}</div>-->
							<el-select v-if="user_group === '管理员' || (form['curriculum_id'] && $check_field('set','teacher')) || (!form['curriculum_id'] && $check_field('add','teacher'))" id="teacher" v-model="form['teacher']" :disabled="disabledObj['teacher_isDisabled']">
								<el-option v-for="o in list_user_teacher" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
							<el-select v-else-if="$check_field('get','teacher')" id="teacher" v-model="form['teacher']" :disabled="true">
								<el-option v-for="o in list_user_teacher" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
						</div>
						<el-select v-else id="teacher" v-model="form['teacher']" :disabled="disabledObj['teacher_isDisabled']">
							<el-option v-for="o in list_user_teacher" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','limited_number_of_people') || $check_field('add','limited_number_of_people') || $check_field('set','limited_number_of_people')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="限定人数" prop="limited_number_of_people">
					<el-input-number id="limited_number_of_people" v-model.number="form['limited_number_of_people']"
						v-if="user_group === '管理员' || (form['curriculum_id'] && $check_field('set','limited_number_of_people')) || (!form['curriculum_id'] && $check_field('add','limited_number_of_people'))"></el-input-number>
					<div v-else-if="$check_field('get','limited_number_of_people')">{{form['limited_number_of_people']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','number_of_people_selected') || $check_field('add','number_of_people_selected') || $check_field('set','number_of_people_selected')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="已选人数" prop="number_of_people_selected">
					<el-input-number id="number_of_people_selected" v-model.number="form['number_of_people_selected']"
						v-if="user_group === '管理员' || (form['curriculum_id'] && $check_field('set','number_of_people_selected')) || (!form['curriculum_id'] && $check_field('add','number_of_people_selected'))"></el-input-number>
					<div v-else-if="$check_field('get','number_of_people_selected')">{{form['number_of_people_selected']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','course_details_') || $check_field('add','course_details_') || $check_field('set','course_details_')" :xs="24" :sm="24" :lg="24" class="el_form_editor_warp">
				<el-form-item label="课程详情" prop="course_details_">
					<quill-editor v-model.number="form['course_details_']"
						v-if="user_group === '管理员' || (form['curriculum_id'] && $check_field('set','course_details_')) || (!form['curriculum_id'] && $check_field('add','course_details_')) ">
					</quill-editor>
					<div v-else-if="$check_field('get','course_details_')" v-html="form['course_details_']"></div>
				</el-form-item>
			</el-col>
      <el-col :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
        <el-form-item label="限制次数" prop="limit_times">
          <el-input id="limit_times" v-model="form['limit_times']" placeholder="限制次数"
                    v-if="user_group === '管理员' || (form['curriculum_id}'] && $check_action('/curriculum/view','set')) || (!form['curriculum_id}'] && $check_action('/curriculum/view','add')) "
          ></el-input>
          <div v-else-if="$check_action('/curriculum/view','get')" v-html="form['limit_times']"></div>
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
				field: "curriculum_id",
				url_add: "~/api/curriculum/add?",
				url_set: "~/api/curriculum/set?",
				url_get_obj: "~/api/curriculum/get_obj?",
				url_upload: "~/api/curriculum/upload?",

				query: {
					"curriculum_id": 0,
				},

				form: {
					"course_number":'', // 课程编号
					"course_name":'', // 课程名称
					"professional_type":'', // 专业类型
					"course_cover":'', // 课程封面
					"suitable_grade":'', // 适学年级
					"teacher": 0, // 教师
					"limited_number_of_people":0, // 限定人数
					"number_of_people_selected":0, // 已选人数
					"course_details_":'', // 课程详情
					"sk_address": '',
					"sk_time": '',
					"score": '',
					"xueshi": '',
					"curriculum_id": 0, // ID
					"limit_times": "", // 限制次数
					"limit_type":2,

				},
				disabledObj:{
					"course_number_isDisabled": false,
					"course_name_isDisabled": false,
					"professional_type_isDisabled": false,
					"course_cover_isDisabled": false,
					"sk_address_isDisabled": false,
					"sk_time_isDisabled": false,
					"score_isDisabled": false,
					"xueshi_isDisabled": false,
					"suitable_grade_isDisabled": false,
					"teacher_isDisabled": false,
					"course_details__isDisabled": false,
					"limit_times_isDisabled": false, // 限制次数
				},
				//专业类型选项列表
				list_professional_type: [],
				// 用户列表
				list_user_teacher: [],
				// 用户组
				group_user_teacher: "",
			}
		},
		methods: {
			/**
			 * 获取专业类型列表
			 */
			async get_list_professional_type() {
				var json = await this.$get("~/api/professional_classification/get_list?");
				if(json.result && json.result.list){
					this.list_professional_type = json.result.list;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			/**
			 * 上传课程封面
			 * @param {Object} param图片参数
			 */
			upload_course_cover(param){
				this.uploadFile(param.file, "course_cover");
			},
			/**
			 * 获取教师用户列表
			 */
			async get_list_user_teacher() {
                // if(this.user_group !== "管理员" && this.form["teacher"] === 0) {
                //     this.form["teacher"] = this.user.user_id;
                // }
                var json = await this.$get("~/api/user/get_list?user_group=教师");
                if(json.result && json.result.list){
                    this.list_user_teacher = json.result.list;
                }
                else if(json.error){
                    console.error(json.error);
                }
			},
			/**
			 * 获取教师用户组
			 */
			async get_group_user_teacher() {
				this.form["teacher"] = this.user.user_id;
				var json = await this.$get("~/api/user_group/get_obj?name=教师");
				if(json.result && json.result.obj){
					this.group_user_teacher = json.result.obj;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			get_user_session_teacher(id){
				var _this = this;
				var user_id = {"user_id":id}
				var url = "~/api/"+_this.group_user_teacher.source_table+"/get_obj?"
				this.$get(url, user_id, function(res) {
					if (res.result && res.result.obj) {
						var arr = []
						for (let key in res.result.obj) {
							arr.push(key)
						}
						var arrForm = []
						for (let key in _this.form) {
							arrForm.push(key)
						}
						for (var i=0;i<arr.length;i++){
							for (var j=0;j<arrForm.length;j++){
								if (arr[i]===arrForm[j]){
									if (arr[i]!=="teacher") {
										_this.form[arrForm[j]] = res.result.obj[arr[i]]
										_this.disabledObj[arrForm[j] + '_isDisabled'] = true
										break;
									}else {
										_this.disabledObj[arrForm[j] + '_isDisabled'] = true
									}
								}
							}
						}
					}
				});
			},
			get_user_teacher(id){
				var obj = this.list_user_teacher.getObj({"user_id":id});
				var ret = "";
				if(obj){
					if(obj.nickname){
						ret = obj.nickname;}
					else{
						ret = obj.username;
					}
				}
				return ret;
			},

			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
				/**
				* 请求列表前
				* @param {Object} param
				*/
				var user_group = this.user.user_group;
				if (user_group !== "管理员") {
					switch (user_group) {
						case "教师":
							if(param["teacher"] > 0){
								param["teacher"] = this.user.user_id;
							}
							break;
					}
				}
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
				$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){
			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/curriculum/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/curriculum/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/curriculum/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/curriculum/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/curriculum/view','get');
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
			this.get_list_professional_type();
			this.get_list_user_teacher();
			this.get_group_user_teacher();
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
