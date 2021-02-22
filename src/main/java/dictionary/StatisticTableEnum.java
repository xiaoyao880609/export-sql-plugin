package dictionary;

import java.util.Arrays;
import java.util.List;

public enum StatisticTableEnum {
    st_student_day_record(
            "syb_whole_student_day_result",
            "st_student_day_record",
            Arrays.asList("id","day_index","login_count","down_count","weike_length","weike_count","test_count","discuss_count","homework_count","class_room_experience_count","classroom_count","user_task_count","ask_count","academic_year","school_id","student_id","sync_flag","grade_code","tenant_code","modify_time","create_time","class_room_train_length","class_room_video_test_count","train_count","class_room_train_count","train_video_count","train_video_length","train_video_test_count","train_sync_test_count","train_sync_test_rate","train_sync_test_question_count","train_sync_test_right_count","train_down_count","megrez_count","megrez_video_count","megrez_video_length","megrez_video_test_count","megrez_sync_test_count","megrez_sync_test_rate","megrez_sync_test_question_count","megrez_sync_test_rigth_count","megrez_down_count"),
            Arrays.asList("id","day_index","login_count","down_count","weike_length","weike_count","test_count","discuss_count","homework_count","class_room_experience_count","classroom_count","user_task_count","ask_count","academic_year","school_id","student_id","sync_flag","grade_code","tenant_code","modify_time","create_time","class_room_train_length","class_room_video_test_count","train_count","class_room_train_count","train_video_count","train_video_length","train_video_test_count","train_sync_test_count","train_sync_test_rate","train_sync_test_question_count","train_sync_test_right_count","train_down_count","megrez_count","megrez_video_count","megrez_video_length","megrez_video_test_count","megrez_sync_test_count","megrez_sync_test_rate","megrez_sync_test_question_count","megrez_sync_test_right_count","megrez_down_count"),
            "day_index"
            ),
    st_student_month_ranking(
            "syb_student_month_rank",
            "st_student_month_ranking",
	        Arrays.asList("id","other_id","month_index","student_id","video_length_rank","homework_count_rank","discuss_count_rank","down_count_rank","exam_count_rank","question_count_rank","task_complete_count_rank","answer_count_rank","ask_question_count_rank","answering_question_count_rank","class_room_count_rank","class_room_activity_count_rank","class_room_action_count_rank","class_room_exp_count_rank","class_room_score_count_rank","class_room_task_complete_rank","class_room_performance_rank","grade_code","specialty_code","academic_year","klass_id","school_id","tenant_code","modify_time","create_time","class_room_train_rank","train_rank","megrez_complete_rank","megrez_video_rank","megrez_question_rank","megrez_question_rate_rank","megrez_down_rank"),
	        Arrays.asList("id","other_id","month_index","student_id","video_length_rank","homework_count_rank","discuss_count_rank","down_count_rank","exam_count_rank","question_count_rank","task_complete_count_rank","answer_count_rank","ask_question_count_rank","answering_question_count_rank","class_room_count_rank","class_room_activity_count_rank","class_room_action_count_rank","class_room_exp_count_rank","class_room_score_count_rank","class_room_task_complete_rank","class_room_performance_rank","grade_code","specialty_code","academic_year","klass_id","school_id","tenant_code","modify_time","create_time","class_room_train_rank","train_rank","megrez_complete_rank","megrez_video_rank","megrez_question_rank","megrez_question_rate_rank","megrez_down_rank"),
	        "month_index"
        ),
    st_student_month_record(
            "syb_student_month_result",
            "st_student_month_record",
            Arrays.asList("id","other_id","month_index","student_id","video_length","video_count","homework_count","discuss_count","down_count","exam_count","question_count","question_right_count","task_complete_count","task_count","resource_count","weike_count","answer_count","ask_question_count","answering_question_count","discuss_participate_count","class_room_count","class_room_activity_count","class_room_action_count","class_room_exp_count","class_room_score_count","class_room_down_count","class_room_video_count","class_room_exam_count","class_room_discuss_count","class_room_homework_count","class_room_task_complete_count","class_room_task_count","class_room_video_length","class_room_totle_down_count","class_room_totle_video_count","class_room_totle_exam_count","class_room_totle_discuss_count","class_room_totle_homework_count","grade_code","specialty_code","academic_year","klass_id","school_id","tenant_code","modify_time","create_time","class_room_train_length","class_room_video_test_count","train_count","class_room_train_count","train_video_count","train_video_length","train_video_test_count","train_sync_test_count","train_sync_test_rate","train_sync_test_question_count","train_down_count","train_sync_test_right_count","megrez_count","megrez_video_count","megrez_video_length","megrez_video_test_count","megrez_sync_test_count","megrez_sync_test_rate","megrez_sync_test_question_count","megrez_sync_test_rigth_count","megrez_down_count"),
            Arrays.asList("id","other_id","month_index","student_id","video_length","video_count","homework_count","discuss_count","down_count","exam_count","question_count","question_right_count","task_complete_count","task_count","resource_count","weike_count","answer_count","ask_question_count","answering_question_count","discuss_participate_count","class_room_count","class_room_activity_count","class_room_action_count","class_room_exp_count","class_room_score_count","class_room_down_count","class_room_video_count","class_room_exam_count","class_room_discuss_count","class_room_homework_count","class_room_task_complete_count","class_room_task_count","class_room_video_length","class_room_totle_down_count","class_room_totle_video_count","class_room_totle_exam_count","class_room_totle_discuss_count","class_room_totle_homework_count","grade_code","specialty_code","academic_year","klass_id","school_id","tenant_code","modify_time","create_time","class_room_train_length","class_room_video_test_count","train_count","class_room_train_count","train_video_count","train_video_length","train_video_test_count","train_sync_test_count","train_sync_test_rate","train_sync_test_question_count","train_down_count","train_sync_test_right_count","megrez_count","megrez_video_count","megrez_video_length","megrez_video_test_count","megrez_sync_test_count","megrez_sync_test_rate","megrez_sync_test_question_count","megrez_sync_test_right_count","megrez_down_count"),
            "month_index"
            ),
    st_student_other_month_ranking(
            "syb_student_other_month_rank",
            "st_student_other_month_ranking",
            Arrays.asList("id","month_index","student_id","score_count_rank","login_count_rank","grade_code","academic_year","klass_id","school_id","tenant_code","modify_time","create_time"),
            Arrays.asList("id","month_index","student_id","score_count_rank","login_count_rank","grade_code","academic_year","klass_id","school_id","tenant_code","modify_time","create_time"),
            "month_index"
            ),
    st_student_other_month_record(
            "syb_student_other_month_result",
            "st_student_other_month_record",
            Arrays.asList("id","month_index","student_id","score_count","login_count","grade_code","academic_year","klass_id","school_id","tenant_code","modify_time","create_time"),
            Arrays.asList("id","month_index","student_id","score_count","login_count","grade_code","academic_year","klass_id","school_id","tenant_code","modify_time","create_time"),
            "month_index"
            ),
    st_student_week_ranking(
            "syb_student_week_rank",
            "st_student_week_ranking",
            Arrays.asList("id","other_id","week_index","student_id","video_length_rank","homework_count_rank","discuss_count_rank","down_count_rank","exam_count_rank","question_count_rank","task_complete_count_rank","answer_count_rank","ask_question_count_rank","answering_question_count_rank","class_room_count_rank","class_room_activity_count_rank","class_room_action_count_rank","class_room_exp_count_rank","class_room_score_count_rank","class_room_task_complete_rank","class_room_performance_rank","grade_code","specialty_code","academic_year","klass_id","school_id","tenant_code","modify_time","create_time","class_room_train_rank","train_rank","megrez_complete_rank","megrez_video_rank","megrez_question_rank","megrez_question_rate_rank","megrez_down_rank"),
            Arrays.asList("id","other_id","week_index","student_id","video_length_rank","homework_count_rank","discuss_count_rank","down_count_rank","exam_count_rank","question_count_rank","task_complete_count_rank","answer_count_rank","ask_question_count_rank","answering_question_count_rank","class_room_count_rank","class_room_activity_count_rank","class_room_action_count_rank","class_room_exp_count_rank","class_room_score_count_rank","class_room_task_complete_rank","class_room_performance_rank","grade_code","specialty_code","academic_year","klass_id","school_id","tenant_code","modify_time","create_time","class_room_train_rank","train_rank","megrez_complete_rank","megrez_video_rank","megrez_question_rank","megrez_question_rate_rank","megrez_down_rank"),
            "week_index"
            ),
    st_student_week_record(
            "syb_student_week_result",
            "st_student_week_record",
            Arrays.asList("id","other_id","week_index","student_id","video_length","video_count","homework_count","discuss_count","down_count","exam_count","question_count","question_right_count","task_complete_count","task_count","resource_count","weike_count","answer_count","ask_question_count","answering_question_count","discuss_participate_count","class_room_count","class_room_activity_count","class_room_action_count","class_room_exp_count","class_room_score_count","class_room_down_count","class_room_video_count","class_room_exam_count","class_room_discuss_count","class_room_homework_count","class_room_task_complete_count","class_room_task_count","class_room_video_length","class_room_totle_down_count","class_room_totle_video_count","class_room_totle_exam_count","class_room_totle_discuss_count","class_room_totle_homework_count","grade_code","specialty_code","academic_year","klass_id","school_id","tenant_code","modify_time","create_time","class_room_train_length","class_room_video_test_count","train_count","class_room_train_count","train_video_count","train_video_length","train_video_test_count","train_sync_test_count","train_sync_test_rate","train_sync_test_question_count","train_sync_test_right_count","train_down_count","megrez_count","megrez_video_count","megrez_video_length","megrez_video_test_count","megrez_sync_test_count","megrez_sync_test_rate","megrez_sync_test_question_count","megrez_sync_test_rigth_count","megrez_down_count"),
            Arrays.asList("id","other_id","week_index","student_id","video_length","video_count","homework_count","discuss_count","down_count","exam_count","question_count","question_right_count","task_complete_count","task_count","resource_count","weike_count","answer_count","ask_question_count","answering_question_count","discuss_participate_count","class_room_count","class_room_activity_count","class_room_action_count","class_room_exp_count","class_room_score_count","class_room_down_count","class_room_video_count","class_room_exam_count","class_room_discuss_count","class_room_homework_count","class_room_task_complete_count","class_room_task_count","class_room_video_length","class_room_totle_down_count","class_room_totle_video_count","class_room_totle_exam_count","class_room_totle_discuss_count","class_room_totle_homework_count","grade_code","specialty_code","academic_year","klass_id","school_id","tenant_code","modify_time","create_time","class_room_train_length","class_room_video_test_count","train_count","class_room_train_count","train_video_count","train_video_length","train_video_test_count","train_sync_test_count","train_sync_test_rate","train_sync_test_question_count","train_sync_test_right_count","train_down_count","megrez_count","megrez_video_count","megrez_video_length","megrez_video_test_count","megrez_sync_test_count","megrez_sync_test_rate","megrez_sync_test_question_count","megrez_sync_test_right_count","megrez_down_count"),
            "week_index"
            ),
    st_student_other_week_ranking(
            "syb_student_other_week_rank",
            "st_student_other_week_ranking",
            Arrays.asList("id","week_index","student_id","score_count_rank","login_count_rank","grade_code","academic_year","klass_id","school_id","tenant_code","modify_time","create_time"),
            Arrays.asList("id","week_index","student_id","score_count_rank","login_count_rank","grade_code","academic_year","klass_id","school_id","tenant_code","modify_time","create_time"),
            "week_index"
            ),
    st_student_other_week_record(
            "syb_student_other_week_result",
            "st_student_other_week_record",
            Arrays.asList("id","week_index","student_id","score_count","login_count","grade_code","academic_year","klass_id","school_id","tenant_code","modify_time","create_time"),
            Arrays.asList("id","week_index","student_id","score_count","login_count","grade_code","academic_year","klass_id","school_id","tenant_code","modify_time","create_time"),
            "week_index"
            ),
    st_student_year_ranking(
            "syb_student_year_rank",
            "st_student_year_ranking",
            Arrays.asList("id","other_id","year_index","student_id","video_length_rank","homework_count_rank","discuss_count_rank","down_count_rank","exam_count_rank","question_count_rank","task_complete_count_rank","answer_count_rank","ask_question_count_rank","answering_question_count_rank","class_room_count_rank","class_room_activity_count_rank","class_room_action_count_rank","class_room_exp_count_rank","class_room_score_count_rank","class_room_task_complete_rank","class_room_performance_rank","grade_code","specialty_code","academic_year","klass_id","school_id","tenant_code","modify_time","create_time","class_room_train_rank","train_rank","megrez_complete_rank","megrez_video_rank","megrez_question_rank","megrez_question_rate_rank","megrez_down_rank"),
            Arrays.asList("id","other_id","year_index","student_id","video_length_rank","homework_count_rank","discuss_count_rank","down_count_rank","exam_count_rank","question_count_rank","task_complete_count_rank","answer_count_rank","ask_question_count_rank","answering_question_count_rank","class_room_count_rank","class_room_activity_count_rank","class_room_action_count_rank","class_room_exp_count_rank","class_room_score_count_rank","class_room_task_complete_rank","class_room_performance_rank","grade_code","specialty_code","academic_year","klass_id","school_id","tenant_code","modify_time","create_time","class_room_train_rank","train_rank","megrez_complete_rank","megrez_video_rank","megrez_question_rank","megrez_question_rate_rank","megrez_down_rank"),
            "year_index"
            ),
    st_student_year_record(
            "syb_student_year_result",
            "st_student_year_record",
            Arrays.asList("id","other_id","year_index","student_id","video_length","video_count","homework_count","discuss_count","down_count","exam_count","question_count","question_right_count","task_complete_count","task_count","resource_count","weike_count","answer_count","ask_question_count","answering_question_count","discuss_participate_count","class_room_count","class_room_activity_count","class_room_action_count","class_room_exp_count","class_room_score_count","class_room_down_count","class_room_video_count","class_room_exam_count","class_room_discuss_count","class_room_homework_count","class_room_task_complete_count","class_room_task_count","class_room_video_length","class_room_totle_down_count","class_room_totle_video_count","class_room_totle_exam_count","class_room_totle_discuss_count","class_room_totle_homework_count","grade_code","specialty_code","academic_year","klass_id","school_id","tenant_code","modify_time","create_time","class_room_train_length","class_room_video_test_count","train_count","class_room_train_count","train_video_count","train_video_length","train_video_test_count","train_sync_test_count","train_sync_test_rate","train_sync_test_question_count","train_sync_test_right_count","train_down_count","megrez_count","megrez_video_count","megrez_video_length","megrez_video_test_count","megrez_sync_test_count","megrez_sync_test_rate","megrez_sync_test_question_count","megrez_sync_test_rigth_count","megrez_down_count"),
            Arrays.asList("id","other_id","year_index","student_id","video_length","video_count","homework_count","discuss_count","down_count","exam_count","question_count","question_right_count","task_complete_count","task_count","resource_count","weike_count","answer_count","ask_question_count","answering_question_count","discuss_participate_count","class_room_count","class_room_activity_count","class_room_action_count","class_room_exp_count","class_room_score_count","class_room_down_count","class_room_video_count","class_room_exam_count","class_room_discuss_count","class_room_homework_count","class_room_task_complete_count","class_room_task_count","class_room_video_length","class_room_totle_down_count","class_room_totle_video_count","class_room_totle_exam_count","class_room_totle_discuss_count","class_room_totle_homework_count","grade_code","specialty_code","academic_year","klass_id","school_id","tenant_code","modify_time","create_time","class_room_train_length","class_room_video_test_count","train_count","class_room_train_count","train_video_count","train_video_length","train_video_test_count","train_sync_test_count","train_sync_test_rate","train_sync_test_question_count","train_sync_test_right_count","train_down_count","megrez_count","megrez_video_count","megrez_video_length","megrez_video_test_count","megrez_sync_test_count","megrez_sync_test_rate","megrez_sync_test_question_count","megrez_sync_test_right_count","megrez_down_count"),
            "year_index"
            ),
    st_student_other_year_ranking(
            "syb_student_other_year_rank",
            "st_student_other_year_ranking",
            Arrays.asList("id","year_index","student_id","score_count_rank","login_count_rank","grade_code","academic_year","klass_id","school_id","tenant_code","modify_time","create_time"),
            Arrays.asList("id","year_index","student_id","score_count_rank","login_count_rank","grade_code","academic_year","klass_id","school_id","tenant_code","modify_time","create_time"),
            "year_index"
            ),
    st_student_other_year_record(
            "syb_student_other_year_result",
            "st_student_other_year_record",
            Arrays.asList("id","year_index","student_id","score_count","login_count","grade_code","academic_year","klass_id","school_id","tenant_code","modify_time","create_time"),
            Arrays.asList("id","year_index","student_id","score_count","login_count","grade_code","academic_year","klass_id","school_id","tenant_code","modify_time","create_time"),
            "year_index"
            ),
    st_school_student_day_record(
            "syb_whole_school_student_day_result",
            "st_school_student_day_record",
            Arrays.asList("id","day_index","login_count","down_count","weike_length","weike_count","test_count","discuss_count","homework_count","class_room_experience_count","classroom_count","user_task_count","ask_count","school_id","sync_flag","tenant_code","modify_time","create_time","megrez_count","megrez_video_count","megrez_video_length","megrez_video_test_count","megrez_sync_test_count","megrez_sync_test_rate","megrez_sync_test_question_count","megrez_sync_test_rigth_count","megrez_down_count","train_count","train_video_length","class_room_train_count","class_room_train_length"),
            Arrays.asList("id","day_index","login_count","down_count","weike_length","weike_count","test_count","discuss_count","homework_count","class_room_experience_count","classroom_count","user_task_count","ask_count","school_id","sync_flag","tenant_code","modify_time","create_time","megrez_count","megrez_video_count","megrez_video_length","megrez_video_test_count","megrez_sync_test_count","megrez_sync_test_rate","megrez_sync_test_question_count","megrez_sync_test_right_count","megrez_down_count","train_count","train_video_length","class_room_train_count","class_room_train_length"),
            "day_index"
            ),
    st_school_teacher_day_record(
            "syb_whole_school_teacher_day_result",
            "st_school_teacher_day_record",
            Arrays.asList("id","day_index","login_count","up_count","down_count","weike_length","weike_count","test_count","lesson_count","classroom_count","answer_count","task_count","task_real_count","homework_count","discuss_count","school_id","arrange_homework_count","arrange_discuss_count","sync_flag","tenant_code","modify_time","create_time","train_count","class_room_train_count","megrez_video_count","megrez_video_length","megrez_down_count"),
            Arrays.asList("id","day_index","login_count","up_count","down_count","weike_length","weike_count","test_count","lesson_count","classroom_count","answer_count","task_count","task_real_count","homework_count","discuss_count","school_id","arrange_homework_count","arrange_discuss_count","sync_flag","tenant_code","modify_time","create_time","train_count","class_room_train_count","megrez_video_count","megrez_video_length","megrez_down_count"),
            "day_index"
            ),
    st_school_month_record(
            "syb_school_month_result",
            "st_school_month_record",
            Arrays.asList("id","other_id","month_index","lesson_count","lesson_totle_count","resource_count","resource_totle_count","class_room_count","class_room_totle_count","task_count","task_totle_count","task_complete_count","task_complete_totle_count","specialty_code","school_id","tenant_code","modify_time","create_time"),
            Arrays.asList("id","other_id","month_index","lesson_count","lesson_totle_count","resource_count","resource_totle_count","class_room_count","class_room_totle_count","task_count","task_totle_count","task_complete_count","task_complete_totle_count","specialty_code","school_id","tenant_code","modify_time","create_time"),
            "month_index"
            ),
    st_school_other_month_record(
            "syb_school_other_month_result",
            "st_school_other_month_record",
            Arrays.asList("id","month_index","teacher_login_count","student_login_count","school_id","tenant_code","modify_time","create_time"),
            Arrays.asList("id","month_index","teacher_login_count","student_login_count","school_id","tenant_code","modify_time","create_time"),
            "month_index"
            ),
    st_school_week_record(
            "syb_school_week_result",
            "st_school_week_record",
            Arrays.asList("id","other_id","week_index","lesson_count","lesson_totle_count","resource_count","resource_totle_count","class_room_count","class_room_totle_count","task_count","task_totle_count","task_complete_count","task_complete_totle_count","specialty_code","school_id","tenant_code","modify_time","create_time"),
            Arrays.asList("id","other_id","week_index","lesson_count","lesson_totle_count","resource_count","resource_totle_count","class_room_count","class_room_totle_count","task_count","task_totle_count","task_complete_count","task_complete_totle_count","specialty_code","school_id","tenant_code","modify_time","create_time"),
            "week_index"
            ),
    st_school_other_week_record(
            "syb_school_other_week_result",
            "st_school_other_week_record",
            Arrays.asList("id","week_index","school_id","teacher_login_count","student_login_count","tenant_code","modify_time","create_time"),
            Arrays.asList("id","week_index","school_id","teacher_login_count","student_login_count","tenant_code","modify_time","create_time"),
            "week_index"
            ),
    st_school_year_record(
            "syb_school_year_result",
            "st_school_year_record",
            Arrays.asList("id","other_id","year_index","lesson_count","lesson_totle_count","resource_count","resource_totle_count","class_room_count","class_room_totle_count","task_count","task_totle_count","task_complete_count","task_complete_totle_count","specialty_code","school_id","tenant_code","modify_time","create_time"),
            Arrays.asList("id","other_id","year_index","lesson_count","lesson_totle_count","resource_count","resource_totle_count","class_room_count","class_room_totle_count","task_count","task_totle_count","task_complete_count","task_complete_totle_count","specialty_code","school_id","tenant_code","modify_time","create_time"),
            "year_index"
            ),
    st_school_other_year_record(
            "syb_school_other_year_result",
            "st_school_other_year_record",
            Arrays.asList("id","year_index","teacher_login_count","student_login_count","school_id","tenant_code","modify_time","create_time"),
            Arrays.asList("id","year_index","teacher_login_count","student_login_count","school_id","tenant_code","modify_time","create_time"),
            "year_index"
            ),
    st_school_total_record(
            "syb_school_total_result",
            "st_school_total_record",
            Arrays.asList("id","lesson_totle_count","resource_totle_count","class_room_totle_count","task_totle_count","task_complete_totle_count","specialty_code","school_id","tenant_code","modify_time","create_time"),
            Arrays.asList("id","lesson_totle_count","resource_totle_count","class_room_totle_count","task_totle_count","task_complete_totle_count","specialty_code","school_id","tenant_code","modify_time","create_time"),
            null
            ),
    st_class_month_ranking(
            "syb_class_month_rank",
            "st_class_month_ranking",
            Arrays.asList("id","other_id","month_index","task_complete_count_grade_rank","answer_count_grade_rank","answer_count_school_rank","ask_question_count_grade_rank","ask_question_count_school_rank","class_room_score_grade_rank","class_room_score_school_rank","class_room_task_complete_grade_rank","class_room_performance_grade_rank","class_room_performance_school_rank","grade_code","specialty_code","academic_year","klass_id","school_id","tenant_code","modify_time","create_time","class_room_train_rank","train_rank","megrez_complete_rank","megrez_video_rank","megrez_question_rank","megrez_question_rate_rank","megrez_down_rank"),
            Arrays.asList("id","other_id","month_index","task_complete_count_grade_rank","answer_count_grade_rank","answer_count_school_rank","ask_question_count_grade_rank","ask_question_count_school_rank","class_room_score_grade_rank","class_room_score_school_rank","class_room_task_complete_grade_rank","class_room_performance_grade_rank","class_room_performance_school_rank","grade_code","specialty_code","academic_year","klass_id","school_id","tenant_code","modify_time","create_time","class_room_train_rank","train_rank","megrez_complete_rank","megrez_video_rank","megrez_question_rank","megrez_question_rate_rank","megrez_down_rank"),
            "month_index"
            ),
    st_class_other_month_ranking(
            "syb_class_other_month_rank",
            "st_class_other_month_ranking",
            Arrays.asList("id","month_index","login_count_grade_rank","login_count_school_rank","score_count_grade_rank","score_count_school_rank","grade_code","academic_year","klass_id","school_id","tenant_code","modify_time","create_time"),
            Arrays.asList("id","month_index","login_count_grade_rank","login_count_school_rank","score_count_grade_rank","score_count_school_rank","grade_code","academic_year","klass_id","school_id","tenant_code","modify_time","create_time"),
            "month_index"
            ),
    st_class_month_record(
            "syb_class_month_result",
            "st_class_month_record",
            Arrays.asList("id","other_id","month_index","student_count","video_length","video_count","homework_count","discuss_count","down_count","exam_count","question_count","question_right_count","task_complete_count","task_count","resource_count","weike_count","answer_count","ask_question_count","discuss_participate_count","class_room_count","class_room_activity_count","class_room_action_count","class_room_exp_count","class_room_score_count","class_room_down_count","class_room_video_count","class_room_exam_count","class_room_discuss_count","class_room_homework_count","class_room_task_complete_count","class_room_task_count","class_room_totle_down_count","class_room_totle_video_count","class_room_totle_exam_count","class_room_totle_discuss_count","class_room_totle_homework_count","class_room_teach_count","grade_code","specialty_code","academic_year","klass_id","school_id","tenant_code","modify_time","create_time","class_room_train_length","class_room_video_test_count","train_count","class_room_train_count","train_video_count","train_video_length","train_video_test_count","train_sync_test_count","train_sync_test_rate","train_sync_test_question_count","train_down_count","train_sync_test_right_count","megrez_count","megrez_video_count","megrez_video_length","megrez_video_test_count","megrez_sync_test_count","megrez_sync_test_rate","megrez_sync_test_question_count","megrez_sync_test_rigth_count","megrez_down_count","class_room_teacher_train_count","teacher_train_count"),
            Arrays.asList("id","other_id","month_index","student_count","video_length","video_count","homework_count","discuss_count","down_count","exam_count","question_count","question_right_count","task_complete_count","task_count","resource_count","weike_count","answer_count","ask_question_count","discuss_participate_count","class_room_count","class_room_activity_count","class_room_action_count","class_room_exp_count","class_room_score_count","class_room_down_count","class_room_video_count","class_room_exam_count","class_room_discuss_count","class_room_homework_count","class_room_task_complete_count","class_room_task_count","class_room_totle_down_count","class_room_totle_video_count","class_room_totle_exam_count","class_room_totle_discuss_count","class_room_totle_homework_count","class_room_teach_count","grade_code","specialty_code","academic_year","klass_id","school_id","tenant_code","modify_time","create_time","class_room_train_length","class_room_video_test_count","train_count","class_room_train_count","train_video_count","train_video_length","train_video_test_count","train_sync_test_count","train_sync_test_rate","train_sync_test_question_count","train_down_count","train_sync_test_right_count","megrez_count","megrez_video_count","megrez_video_length","megrez_video_test_count","megrez_sync_test_count","megrez_sync_test_rate","megrez_sync_test_question_count","megrez_sync_test_right_count","megrez_down_count","class_room_teacher_train_count","teacher_train_count"),
            "month_index"
            ),
    st_class_other_month_record(
            "syb_class_other_month_result",
            "st_class_other_month_record",
            Arrays.asList("id","month_index","score_count","login_count","grade_code","academic_year","klass_id","school_id","tenant_code","modify_time","create_time"),
            Arrays.asList("id","month_index","score_count","login_count","grade_code","academic_year","klass_id","school_id","tenant_code","modify_time","create_time"),
            "month_index"
            ),
    st_class_week_ranking(
            "syb_class_week_rank",
            "st_class_week_ranking",
            Arrays.asList("id","other_id","week_index","task_complete_count_grade_rank","answer_count_grade_rank","answer_count_school_rank","ask_question_count_grade_rank","ask_question_count_school_rank","class_room_score_grade_rank","class_room_score_school_rank","class_room_task_complete_grade_rank","class_room_performance_grade_rank","class_room_performance_school_rank","grade_code","specialty_code","academic_year","klass_id","school_id","tenant_code","modify_time","create_time","class_room_train_rank","train_rank","megrez_complete_rank","megrez_video_rank","megrez_question_rank","megrez_question_rate_rank","megrez_down_rank"),
            Arrays.asList("id","other_id","week_index","task_complete_count_grade_rank","answer_count_grade_rank","answer_count_school_rank","ask_question_count_grade_rank","ask_question_count_school_rank","class_room_score_grade_rank","class_room_score_school_rank","class_room_task_complete_grade_rank","class_room_performance_grade_rank","class_room_performance_school_rank","grade_code","specialty_code","academic_year","klass_id","school_id","tenant_code","modify_time","create_time","class_room_train_rank","train_rank","megrez_complete_rank","megrez_video_rank","megrez_question_rank","megrez_question_rate_rank","megrez_down_rank"),
            "week_index"
            ),
    st_class_other_week_ranking(
            "syb_class_other_week_rank",
            "st_class_other_week_ranking",
            Arrays.asList("id","week_index","login_count_grade_rank","login_count_school_rank","score_count_grade_rank","score_count_school_rank","grade_code","academic_year","klass_id","school_id","tenant_code","modify_time","create_time"),
            Arrays.asList("id","week_index","login_count_grade_rank","login_count_school_rank","score_count_grade_rank","score_count_school_rank","grade_code","academic_year","klass_id","school_id","tenant_code","modify_time","create_time"),
            "week_index"
            ),
    st_class_week_record(
            "syb_class_week_result",
            "st_class_week_record",
            Arrays.asList("id","other_id","week_index","student_count","video_length","video_count","homework_count","discuss_count","down_count","exam_count","question_count","question_right_count","task_complete_count","task_count","resource_count","weike_count","answer_count","ask_question_count","discuss_participate_count","class_room_count","class_room_activity_count","class_room_action_count","class_room_exp_count","class_room_score_count","class_room_down_count","class_room_video_count","class_room_exam_count","class_room_discuss_count","class_room_homework_count","class_room_task_complete_count","class_room_task_count","class_room_totle_down_count","class_room_totle_video_count","class_room_totle_exam_count","class_room_totle_discuss_count","class_room_totle_homework_count","class_room_teach_count","grade_code","specialty_code","academic_year","klass_id","school_id","tenant_code","modify_time","create_time","class_room_train_count","class_room_train_length","class_room_video_test_count","train_count","train_video_count","train_video_length","train_video_test_count","train_sync_test_count","train_sync_test_rate","train_sync_test_question_count","train_down_count","train_sync_test_right_count","megrez_count","megrez_video_count","megrez_video_length","megrez_video_test_count","megrez_sync_test_count","megrez_sync_test_rate","megrez_sync_test_question_count","megrez_sync_test_rigth_count","megrez_down_count","class_room_teacher_train_count","teacher_train_count"),
            Arrays.asList("id","other_id","week_index","student_count","video_length","video_count","homework_count","discuss_count","down_count","exam_count","question_count","question_right_count","task_complete_count","task_count","resource_count","weike_count","answer_count","ask_question_count","discuss_participate_count","class_room_count","class_room_activity_count","class_room_action_count","class_room_exp_count","class_room_score_count","class_room_down_count","class_room_video_count","class_room_exam_count","class_room_discuss_count","class_room_homework_count","class_room_task_complete_count","class_room_task_count","class_room_totle_down_count","class_room_totle_video_count","class_room_totle_exam_count","class_room_totle_discuss_count","class_room_totle_homework_count","class_room_teach_count","grade_code","specialty_code","academic_year","klass_id","school_id","tenant_code","modify_time","create_time","class_room_train_count","class_room_train_length","class_room_video_test_count","train_count","train_video_count","train_video_length","train_video_test_count","train_sync_test_count","train_sync_test_rate","train_sync_test_question_count","train_down_count","train_sync_test_right_count","megrez_count","megrez_video_count","megrez_video_length","megrez_video_test_count","megrez_sync_test_count","megrez_sync_test_rate","megrez_sync_test_question_count","megrez_sync_test_right_count","megrez_down_count","class_room_teacher_train_count","teacher_train_count"),
            "week_index"
            ),
    st_class_year_ranking(
            "syb_class_year_rank",
            "st_class_year_ranking",
            Arrays.asList("id","other_id","year_index","task_complete_count_grade_rank","answer_count_grade_rank","answer_count_school_rank","ask_question_count_grade_rank","ask_question_count_school_rank","class_room_score_grade_rank","class_room_score_school_rank","class_room_task_complete_grade_rank","class_room_performance_grade_rank","class_room_performance_school_rank","grade_code","specialty_code","academic_year","klass_id","school_id","tenant_code","modify_time","create_time","class_room_train_rank","train_rank","megrez_complete_rank","megrez_video_rank","megrez_question_rank","megrez_question_rate_rank","megrez_down_rank"),
            Arrays.asList("id","other_id","year_index","task_complete_count_grade_rank","answer_count_grade_rank","answer_count_school_rank","ask_question_count_grade_rank","ask_question_count_school_rank","class_room_score_grade_rank","class_room_score_school_rank","class_room_task_complete_grade_rank","class_room_performance_grade_rank","class_room_performance_school_rank","grade_code","specialty_code","academic_year","klass_id","school_id","tenant_code","modify_time","create_time","class_room_train_rank","train_rank","megrez_complete_rank","megrez_video_rank","megrez_question_rank","megrez_question_rate_rank","megrez_down_rank"),
            "year_index"
            ),
    st_class_year_record(
            "syb_class_year_result",
            "st_class_year_record",
            Arrays.asList("id","other_id","year_index","student_count","video_length","video_count","homework_count","discuss_count","down_count","exam_count","question_count","question_right_count","task_complete_count","task_count","resource_count","weike_count","answer_count","ask_question_count","discuss_participate_count","class_room_count","class_room_activity_count","class_room_action_count","class_room_exp_count","class_room_score_count","class_room_down_count","class_room_video_count","class_room_exam_count","class_room_discuss_count","class_room_homework_count","class_room_task_complete_count","class_room_task_count","class_room_totle_down_count","class_room_totle_video_count","class_room_totle_exam_count","class_room_totle_discuss_count","class_room_totle_homework_count","class_room_teach_count","grade_code","specialty_code","academic_year","klass_id","school_id","tenant_code","modify_time","create_time","class_room_train_length","class_room_video_test_count","train_count","class_room_train_count","train_video_count","train_video_length","train_video_test_count","train_sync_test_count","train_sync_test_rate","train_sync_test_question_count","train_down_count","train_sync_test_right_count","megrez_count","megrez_video_count","megrez_video_length","megrez_video_test_count","megrez_sync_test_count","megrez_sync_test_rate","megrez_sync_test_question_count","megrez_sync_test_rigth_count","megrez_down_count","class_room_teacher_train_count","teacher_train_count"),
            Arrays.asList("id","other_id","year_index","student_count","video_length","video_count","homework_count","discuss_count","down_count","exam_count","question_count","question_right_count","task_complete_count","task_count","resource_count","weike_count","answer_count","ask_question_count","discuss_participate_count","class_room_count","class_room_activity_count","class_room_action_count","class_room_exp_count","class_room_score_count","class_room_down_count","class_room_video_count","class_room_exam_count","class_room_discuss_count","class_room_homework_count","class_room_task_complete_count","class_room_task_count","class_room_totle_down_count","class_room_totle_video_count","class_room_totle_exam_count","class_room_totle_discuss_count","class_room_totle_homework_count","class_room_teach_count","grade_code","specialty_code","academic_year","klass_id","school_id","tenant_code","modify_time","create_time","class_room_train_length","class_room_video_test_count","train_count","class_room_train_count","train_video_count","train_video_length","train_video_test_count","train_sync_test_count","train_sync_test_rate","train_sync_test_question_count","train_down_count","train_sync_test_right_count","megrez_count","megrez_video_count","megrez_video_length","megrez_video_test_count","megrez_sync_test_count","megrez_sync_test_rate","megrez_sync_test_question_count","megrez_sync_test_right_count","megrez_down_count","class_room_teacher_train_count","teacher_train_count"),
            "year_index"
            ),
    st_teacher_day_record(
            "syb_whole_teacher_day_result",
            "st_teacher_day_record",
            Arrays.asList("id","day_index","login_count","up_count","down_count","weike_length","weike_count","test_count","lesson_count","classroom_count","answer_count","task_count","task_real_count","homework_count","discuss_count","academic_year","school_id","teacher_id","sync_flag","specialty_code","tenant_code","modify_time","create_time","train_count","class_room_train_count","megrez_video_count","megrez_video_length","megrez_down_count"),
            Arrays.asList("id","day_index","login_count","up_count","down_count","weike_length","weike_count","test_count","lesson_count","classroom_count","answer_count","task_count","task_real_count","homework_count","discuss_count","academic_year","school_id","teacher_id","sync_flag","specialty_code","tenant_code","modify_time","create_time","train_count","class_room_train_count","megrez_video_count","megrez_video_length","megrez_down_count"),
            "day_index"
            ),
    st_teacher_month_record(
            "syb_teacher_month_result",
            "st_teacher_month_record",
            Arrays.asList("id","month_index","teacher_id","login_count","lesson_count","exam_count","class_room_count","arrange_task_count","check_task_count","resource_count","down_count","ask_question_count","megrez_down_count","specialty_code","academic_year","school_id","tenant_code","modify_time","create_time"),
            Arrays.asList("id","month_index","teacher_id","login_count","lesson_count","exam_count","class_room_count","arrange_task_count","check_task_count","resource_count","down_count","ask_question_count","megrez_down_count","specialty_code","academic_year","school_id","tenant_code","modify_time","create_time"),
            "month_index"
            ),
    st_teacher_week_record(
            "syb_teacher_week_result",
            "st_teacher_week_record",
            Arrays.asList("id","week_index","teacher_id","login_count","lesson_count","exam_count","class_room_count","arrange_task_count","check_task_count","resource_count","down_count","ask_question_count","megrez_down_count","specialty_code","academic_year","school_id","tenant_code","modify_time","create_time"),
            Arrays.asList("id","week_index","teacher_id","login_count","lesson_count","exam_count","class_room_count","arrange_task_count","check_task_count","resource_count","down_count","ask_question_count","megrez_down_count","specialty_code","academic_year","school_id","tenant_code","modify_time","create_time"),
            "week_index"
            ),
    st_teacher_year_record(
            "syb_teacher_year_result",
            "st_teacher_year_record",
            Arrays.asList("id","year_index","teacher_id","login_count","lesson_count","exam_count","class_room_count","arrange_task_count","check_task_count","resource_count","down_count","ask_question_count","megrez_down_count","specialty_code","academic_year","school_id","tenant_code","modify_time","create_time"),
            Arrays.asList("id","year_index","teacher_id","login_count","lesson_count","exam_count","class_room_count","arrange_task_count","check_task_count","resource_count","down_count","ask_question_count","megrez_down_count","specialty_code","academic_year","school_id","tenant_code","modify_time","create_time"),
            "year_index"
            );
    /*xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx(
            "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx",
            "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx",
            Arrays.asList(),
            Arrays.asList(),
            "xxxxxxxx"
            ),
    xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx(
            "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx",
            "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx",
            Arrays.asList(),
            Arrays.asList(),
            "xxxxxxxx"
            )*/
    
    private String                                 table;
    private String                                 targetTable;
    private List<String>                           columns;
    private List<String>                           targetColumns;
    private String                                 filterColumn;
    
	private StatisticTableEnum(String table, String targetTable, List<String> columns, List<String> targetColumns, String filterColumn) {
		this.table = table;
		this.targetTable = targetTable;
		this.columns = columns;
		this.targetColumns = targetColumns;
		this.filterColumn = filterColumn;
	}
    
	public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getTargetTable() {
        return targetTable;
    }

    public void setTargetTable(String targetTable) {
        this.targetTable = targetTable;
    }

    public List<String> getColumns() {
        return columns;
    }

    public void setColumns(List<String> columns) {
        this.columns = columns;
    }

    public List<String> getTargetColumns() {
        return targetColumns;
    }

    public void setTargetColumns(List<String> targetColumns) {
        this.targetColumns = targetColumns;
    }

    public String getFilterColumn() {
        return filterColumn;
    }

    public void setFilterColumn(String filterColumn) {
        this.filterColumn = filterColumn;
    }

    public static List<StatisticTableEnum> getEnumValues() {
        return Arrays.asList(values());
    }

}
