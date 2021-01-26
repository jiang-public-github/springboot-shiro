package com.shiro.common.returns;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;

import java.io.Serializable;

/**
 * @Author jianglanglang
 * @Date 2020/11/8 13:49
 * @Description 接口返回列表的统一格式
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsonResult<T> implements Serializable {
        private static final long serialVersionUID = 113323427779853001L;

        @ApiModelProperty(value = "结果码")
        private Integer code;

        @ApiModelProperty(value = "返回内容")
        private String msg;

        @ApiModelProperty(value = "总记录数")
        private Integer count ;

        @ApiModelProperty(value = "返回数据")
        private T data;


        /**
         * 操作成功无返回数据
         * @return
         */
        public static JsonResult success() {
                return new JsonResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), 0, null);
        }

        /**
         * 操作成功有返回数据
         * @return
         */
        public static JsonResult success(Integer code, String msg, Integer count, Object data) {
                return new JsonResult(code, msg, count, data);
        }

        /**
         * 操作成功有返回数据
         * @return
         */
        public static JsonResult success(Integer count, Object data) {
                return new JsonResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), count, data);
        }

        /**
         * 操作成功有返回数据
         * @return
         */
        public static JsonResult success(Object data) {
                return new JsonResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), 0, data);
        }

        /**
         * 操作成功有无返回数据
         * @return
         */
        public static JsonResult success(Integer code, String msg) {
                return new JsonResult(code, msg, 0, null);
        }

        /**
         * 操作失败无返回数据
         * @return
         */
        public static JsonResult failure() {
                return new JsonResult(ResultCode.FAILURE.getCode(), ResultCode.FAILURE.getMessage(), 0, null);
        }

        /**
         * 操作失败无返回数据
         * @return
         */
        public static JsonResult failure(Integer code, String msg) {
                return new JsonResult(code, msg, 0, null);
        }

        /**
        * 操作失败有返回数据
        * @return
        */
        public static JsonResult failure(Integer code, String msg, Integer count, Object data) {
                return new JsonResult(code, msg, count, data);
        }

        /**
        * 操作失败有返回数据
        * @return
        */
        public static JsonResult failure(Integer count, Object data) {
                return new JsonResult(ResultCode.FAILURE.getCode(), ResultCode.FAILURE.getMessage(), count, data);
        }

        /**
        * 操作失败有返回数据
        * @return
        */
        public static JsonResult failure(Object data) {
                return new JsonResult(ResultCode.FAILURE.getCode(), ResultCode.FAILURE.getMessage(), 0, data);
        }

        /**
        * 请求参数错误
        * @return
        */
        public static JsonResult paramsError(BindingResult bindingResult) {
                return new JsonResult(ResultCode.PARAM_IS_BLANK.getCode(), bindingResult.getFieldError().getDefaultMessage(), 0, null);
        }
}