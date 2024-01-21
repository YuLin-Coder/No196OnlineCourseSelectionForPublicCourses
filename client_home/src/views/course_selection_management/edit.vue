<template>
	<div class="diy_edit page_course_selection_management" id="course_selection_management_edit">
		<div class='warp'>
			<div class='container'>
				<div class='row diy_edit_content_box'>
					<div v-if="$check_field('set','course_number') || $check_field('add','course_number') || $check_field('get','course_number')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>课程编号:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_course_number" v-model="form['course_number']" placeholder="请输入课程编号" v-if="(form['course_number'] && $check_field('set','course_number')) || (!form['course_number'] && $check_field('add','course_number'))"  :disabled="disabledObj['course_number_isDisabled']"/>
							<span v-else-if="$check_field('get','course_number')">{{ form[''] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','course_name') || $check_field('add','course_name') || $check_field('get','course_name')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>课程名称:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_course_name" v-model="form['course_name']" placeholder="请输入课程名称" v-if="(form['course_name'] && $check_field('set','course_name')) || (!form['course_name'] && $check_field('add','course_name'))"  :disabled="disabledObj['course_name_isDisabled']"/>
							<span v-else-if="$check_field('get','course_name')">{{ form[''] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','professional_type') || $check_field('add','professional_type') || $check_field('get','professional_type')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>专业类型:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_professional_type" v-model="form['professional_type']" placeholder="请输入专业类型" v-if="(form['professional_type'] && $check_field('set','professional_type')) || (!form['professional_type'] && $check_field('add','professional_type'))"  :disabled="disabledObj['professional_type_isDisabled']"/>
							<span v-else-if="$check_field('get','professional_type')">{{ form[''] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','suitable_grade') || $check_field('add','suitable_grade') || $check_field('get','suitable_grade')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>适学年级:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_suitable_grade" v-model="form['suitable_grade']" placeholder="请输入适学年级" v-if="(form['suitable_grade'] && $check_field('set','suitable_grade')) || (!form['suitable_grade'] && $check_field('add','suitable_grade'))"  :disabled="disabledObj['suitable_grade_isDisabled']"/>
							<span v-else-if="$check_field('get','suitable_grade')">{{ form[''] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','limited_number_of_people') || $check_field('add','limited_number_of_people') || $check_field('get','limited_number_of_people')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>限定人数:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_limited_number_of_people" v-model="form['limited_number_of_people']" placeholder="请输入限定人数" v-if="(form['limited_number_of_people'] && $check_field('set','limited_number_of_people')) || (!form['limited_number_of_people'] && $check_field('add','limited_number_of_people'))"  :disabled="disabledObj['limited_number_of_people_isDisabled']"/>
							<span v-else-if="$check_field('get','limited_number_of_people')">{{ form[''] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','teacher') || $check_field('add','teacher') || $check_field('get','teacher')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>教师:
							</span>
						</div>
						<div class="diy_field diy_down">
							<select id="form_teacher" :disabled="disabledObj['teacher_isDisabled']" v-model="form['teacher']" v-if="(form['teacher'] && $check_field('set','teacher')) || (!form['teacher'] && $check_field('add','teacher'))" >
								<option v-for="o in list_user_teacher" :value="o['user_id']">
									{{o['nickname'] + '-' + o['username']}}
								</option>
							</select>
							<span v-else-if="$check_field('get','teacher')">{{ form['teacher'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','student') || $check_field('add','student') || $check_field('get','student')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>学生:
							</span>
						</div>
						<div class="diy_field diy_down">
							<select id="form_student" :disabled="disabledObj['student_isDisabled']" v-model="form['student']" v-if="(form['student'] && $check_field('set','student')) || (!form['student'] && $check_field('add','student'))" >
								<option v-for="o in list_user_student" :value="o['user_id']">
									{{o['nickname'] + '-' + o['username']}}
								</option>
							</select>
							<span v-else-if="$check_field('get','student')">{{ form['student'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','student_number') || $check_field('add','student_number') || $check_field('get','student_number')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>学号:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_student_number" v-model="form['student_number']" placeholder="请输入学号" v-if="(form['student_number'] && $check_field('set','student_number')) || (!form['student_number'] && $check_field('add','student_number'))"  :disabled="disabledObj['student_number_isDisabled']"/>
							<span v-else-if="$check_field('get','student_number')">{{ form[''] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','class_number') || $check_field('add','class_number') || $check_field('get','class_number')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>班号:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_class_number" v-model="form['class_number']" placeholder="请输入班号" v-if="(form['class_number'] && $check_field('set','class_number')) || (!form['class_number'] && $check_field('add','class_number'))"  :disabled="disabledObj['class_number_isDisabled']"/>
							<span v-else-if="$check_field('get','class_number')">{{ form[''] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','number_of_people') || $check_field('add','number_of_people') || $check_field('get','number_of_people')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>人数:
							</span>
						</div>
						<!-- 选项 -->
						<div class="diy_field diy_down">
							<select id="form_number_of_people" v-model="form['number_of_people']" v-if="(form['number_of_people'] && $check_field('set','number_of_people')) || (!form['number_of_people'] && $check_field('add','number_of_people'))" >
								<option v-for="o in list_number_of_people" :value="o">
									{{o}}
								</option>
							</select>
							<span v-else-if="$check_field('get','number_of_people')">{{ form['number_of_people'] }}</span>
						</div>
					</div>
				</div>
				<div class="diy_edit_submit_box row">
					<div class="col-12">
						<div class="btn_box">
							<button class="btn_submit" @click="submit()">提交</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	import mixin from "@/mixins/page.js";
	export default {
		mixins: [mixin],
		components: {},
		data() {
			return {
				url_get_obj: "~/api/course_selection_management/get_obj?",
				url_add: "~/api/course_selection_management/add?",
				url_set: "~/api/course_selection_management/set?",

				// 登录权限
				oauth: {
					"signIn": true,
					"user_group": []
				},

				// 查询条件
				query: {
					"course_number": "",
					"course_name": "",
					"professional_type": "",
					"suitable_grade": "",
					"limited_number_of_people": "",
					"teacher": 0,
					"student": 0,
					"student_number": "",
					"class_number": "",
					"number_of_people": "",
					"course_selection_management_id": 0,
				},

				obj: {
					"course_number":'', // 课程编号
					"course_name":'', // 课程名称
					"professional_type":'', // 专业类型
					"suitable_grade":'', // 适学年级
					"limited_number_of_people":'', // 限定人数
					"teacher": 0, // 教师
					"student": 0, // 学生
					"student_number":'', // 学号
					"class_number":'', // 班号
					"number_of_people":'', // 人数
					"course_selection_management_id": 0,
          "user_id": this.$store.state.user.user_id,
				},

				// 表单字段
				form: {
					"course_number":'', // 课程编号
					"course_name":'', // 课程名称
					"professional_type":'', // 专业类型
					"suitable_grade":'', // 适学年级
					"limited_number_of_people":'', // 限定人数
					"teacher": 0, // 教师
					"student": 0, // 学生
					"student_number":'', // 学号
					"class_number":'', // 班号
					"number_of_people":'', // 人数
					"course_selection_management_id": 0,
          "user_id": this.$store.state.user.user_id,
				},
				disabledObj:{
					"course_number_isDisabled": false,
					"course_name_isDisabled": false,
					"professional_type_isDisabled": false,
					"suitable_grade_isDisabled": false,
					"limited_number_of_people_isDisabled": false,
					"teacher_isDisabled": false,
					"student_isDisabled": false,
					"student_number_isDisabled": false,
					"class_number_isDisabled": false,
					"number_of_people_isDisabled": false,
				},
				// 用户列表
				list_user_teacher: [],
				// 用户列表
				list_user_student: [],
				//人数选项列表
				list_number_of_people: ['1'],

				// ID字段
				field: "course_selection_management_id",
        limit_times: 0, // 限制次数
        limit_type:0,
        act_limit_times:0,
			}
		},
		methods: {
      /**
       * 提交前验证事件
       * @param {Object} 请求参数
       * @return {String} 验证成功返回null, 失败返回错误提示
       */
      submit_check(param) {
        if(this.act_limit_times>=this.limit_times){
          return "已超过提交次数";
        }
        return null;
      },

      check_limit(){
        /**
         * 提交前验证事件
         * @param {Object} 请求参数
         * @return {String} 验证成功返回null, 失败返回错误提示
         */
        let _this = this;
        if (_this.$store.state.user.user_group==='管理员'){
          _this.limit_times = 999;
        }else {
          _this.$get("~/api/curriculum/get_obj?",
              {"course_number":_this.form.course_number}, function(res) {
            if (res.result && res.result.obj) {
              _this.limit_times=res.result.obj.limit_times;
              _this.limit_type=res.result.obj.limit_type;
              let user_id = _this.$store.state.user.user_id;
              let limit_url = "~/api/course_selection_management/count?course_number="+_this.form.course_number+"&user_id="+user_id;
              if (_this.limit_type === 1){
                let day = _this.$toTime(parseInt((new Date()).getTime()), "yyyy-MM-dd");
                let time = "&create_time_min="+day+" 00:00:00&create_time_max="+day+" 23:59:59"
                limit_url = limit_url +time;
              }
              _this.$get(limit_url ,{}, function(res) {
                _this.act_limit_times = res.result;
              });
            }
          });
        }
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
			 * 获取学生用户列表
			 */
			async get_list_user_student() {
				// if(this.user_group !== "管理员" && this.form["student"] === 0) {
				//     this.form["student"] = this.user.user_id;
				// }
				var json = await this.$get("~/api/user/get_list?user_group=学生");
				if(json.result && json.result.list){
					this.list_user_student = json.result.list;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			async get_user_session_student(){
				var _this = this;
				var json = await this.$get("~/api/user_group/get_obj?name=学生");
				if(json.result && json.result.obj){
					var source_table = json.result.obj.source_table;
					var user_id = _this.$store.state.user.user_id;
					if (user_id){
						var url = "~/api/"+source_table+"/get_obj?"
						this.$get(url, {"user_id":_this.$store.state.user.user_id}, function(res) {
							if (res.result && res.result.obj) {
								var arr = []
								for (let key in res.result.obj) {
									arr.push(key)
								}
								var arrForm = []
								for (let key in _this.form) {
									arrForm.push(key)
								}
								_this.form["student"] = user_id
								_this.disabledObj['student' + '_isDisabled'] = true
								for (var i=0;i<arr.length;i++){
									for (var j=0;j<arrForm.length;j++){
										if (arr[i]===arrForm[j]){
											if (arr[i]!=="student") {
												_this.form[arrForm[j]] = res.result.obj[arr[i]]
												_this.disabledObj[arrForm[j] + '_isDisabled'] = true
												break;
											}
										}
									}
								}
							}
						});
					}
				}
				else if(json.error){
					console.error(json.error);
				}
			},

			/**
			 * 修改文件
			 * @param { Object } files 上传文件对象
			 * @param { String } str 表单的属性名
			 */
			change_file(files, str) {
				var form = new FormData();
				form.append("file", files[0]);
				this.$post("~/api/course_selection_management/upload?", form, (res) => {
					if (res.result) {
						this.form[str] = res.result.url;
					} else if (res.error) {
						this.$toast(res.error.message);
					}
				});
			},

			/**
			 * 获取对象后获取缓存表单
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_before(param){
				var form = $.db.get("form");
				if (form) {
					this.obj = $.push(this.obj ,form);
					this.form = $.push(this.form ,form);
				}
				var arr = []
				for (let key in form) {
					arr.push(key)
				}
				for (var i=0;i<arr.length;i++){
					this.disabledObj[arr[i] + '_isDisabled'] = true
				}
				return param;
			},

			/**
			 * 获取对象后获取缓存表单
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json ,func){
				var form = $.db.get("form");
				var obj = Object.assign({} ,form ,this.obj);
				if (form) {
					this.obj = $.push(this.obj ,obj);
				}
				if (form) {
					this.form = $.push(this.form ,form);
				}
				if(func){
					func(json);
				}
			},

		},
		created() {
			this.get_list_user_teacher();
			this.get_user_session_student();
			this.get_list_user_student();
		},
    watch: {
      'form.course_number': {
        handler: function() {
          this.check_limit();
        }
      }
    }
	}
</script>

<style>
</style>
