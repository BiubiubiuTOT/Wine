package com.lgh.wine.utils;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.lgh.wine.R;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/**
 * 选择框帮助类
 * Created by lxx on 2018/3/13.
 */

public class PickerDialogHelper {
    public static final String[] sex = {"男", "女"};

    public static void showTimePicker(Context context, Calendar currentDate, Calendar startDate, Calendar endDate, boolean isDialog, OnTimeSelectListener listener) {
        TimePickerView timePickerView = new TimePickerBuilder(context, listener)
                .setType(new boolean[]{true, true, true, false, false, false})// 年月日时分秒
                .setOutSideCancelable(true)
                .setDate(currentDate)
                .isCyclic(false)
                .isCenterLabel(true) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setLabel("年", "月", "日", "时", "分", "秒")//默认设置为年月日时分秒
                .setRangDate(startDate, endDate)
                .setSubmitColor(ContextCompat.getColor(context, R.color.color_button))//确定按钮文字颜色
                .setCancelColor(ContextCompat.getColor(context, R.color.color_button))//取消按钮文字颜色
                .setTextColorCenter(ContextCompat.getColor(context, R.color.color_button))//选中颜色
                .isDialog(isDialog)//是否显示为对话框样式
                .build();
        timePickerView.show();
    }

    public static void showTimePicker(Context context, Calendar currentDate, boolean isDialog, OnTimeSelectListener listener) {
        TimePickerView timePickerView = new TimePickerBuilder(context, listener)
                .setType(new boolean[]{true, true, true, false, false, false})// 年月日时分秒
                .setDate(currentDate)
                .isCyclic(false)
                .isCenterLabel(true)
                .setSubmitColor(ContextCompat.getColor(context, R.color.color_button))//确定按钮文字颜色
                .setCancelColor(ContextCompat.getColor(context, R.color.color_button))//取消按钮文字颜色
                .setTextColorCenter(ContextCompat.getColor(context, R.color.color_button))//选中颜色
                .isDialog(isDialog)//是否显示为对话框样式
                .build();
        timePickerView.show();
    }

    public static void showTimeYearMonthPicker(Context context, Calendar currentDate, Calendar startDate, Calendar endDate, boolean isDialog, OnTimeSelectListener listener) {
        TimePickerView timePickerView = new TimePickerBuilder(context, listener)
                .setType(new boolean[]{true, true, false, false, false, false})// 年月日时分秒
                .setOutSideCancelable(true)
                .setDate(currentDate)
                .isCyclic(false)
                .isCenterLabel(true) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setLabel("年", "月", "日", "时", "分", "秒")//默认设置为年月日时分秒
                .setRangDate(startDate, endDate)
                .setSubmitColor(ContextCompat.getColor(context, R.color.color_button))//确定按钮文字颜色
                .setCancelColor(ContextCompat.getColor(context, R.color.color_button))//取消按钮文字颜色
                .setTextColorCenter(ContextCompat.getColor(context, R.color.color_button))//选中颜色
                .isDialog(isDialog)//是否显示为对话框样式
                .build();
        timePickerView.show();
    }

    public static void showPickerOption(Context context, final List<String> items, int select, boolean isDialog, OnOptionsSelectListener listener) {
        OptionsPickerView optionsPickerView = new OptionsPickerBuilder(context, listener)
                .setCyclic(false, false, false) //循环与否
                .setSubmitColor(ContextCompat.getColor(context, R.color.color_button))//确定按钮文字颜色
                .setCancelColor(ContextCompat.getColor(context, R.color.color_button))//取消按钮文字颜色
                .setTextColorCenter(ContextCompat.getColor(context, R.color.color_button))//选中颜色
                .setSelectOptions(select)
                .isCenterLabel(true)
                .isDialog(isDialog)
                .build();
        optionsPickerView.setPicker(items);
        optionsPickerView.show();
    }

    public static void showSexOption(Context context, OnOptionsSelectListener listener) {
        OptionsPickerView optionsPickerView = new OptionsPickerBuilder(context, listener)
                .setCyclic(false, false, false) //循环与否
                .setSubmitColor(ContextCompat.getColor(context, R.color.color_button))//确定按钮文字颜色
                .setCancelColor(ContextCompat.getColor(context, R.color.color_button))//取消按钮文字颜色
                .setTextColorCenter(ContextCompat.getColor(context, R.color.color_button))//选中颜色
                .isCenterLabel(true)
                .build();
        optionsPickerView.setPicker(Arrays.asList(sex));
        optionsPickerView.show();
    }

}
