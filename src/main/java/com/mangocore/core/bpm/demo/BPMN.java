package com.mangocore.core.bpm.demo;

/**
 * Created by notreami on 17/11/21.
 */
public class BPMN {


    public static class General {

        public static class Activities {
            private String activity;
            //activity的类型
            private String task;//process
            private String subProcess;//call Activity
            private String transaction;//事务子流程
            private String adHoc_SubProcess;//点对点
            private String loopActivity;//循环
            private String multipleInstancesActivity;//多个实例
        }

        public static class Swimlanes {
            private String pool;//池：表示流程中的主要参与者，典型地，用来分开不同的组织。一个池可容纳一个或多个道（像真实的泳池一样）。
            private String lane;//道：在池中，用于活动按职能或角色归类。绘做按池的长或宽展开的矩形。道包含流对象、连接对象和人造物。
        }

        public static class hhh{
            private String conversation;
            private String callConversation;
            private String subConversation;
        }

        public static class Data{

        }

        public static class Marker{
            private String subProcessMarker;
            private String LoopMarker;
            private String parallelMIMarker;
            private String sequentMIMarker;
            private String adHocMarker;
            private String compenMarker;
        }

        public static class Task {
            private String sendTask;//发送任务
            private String receiveTask;//状态任务，一般表示活动状态，需signal进行转换
            private String userTask;//人机交互任务
            private String manualTask;//线下手工执行任务
            private String businessRuleTask;//业务规则任务
            private String serviceTask;//服务任务
            private String scriptTask;//脚本任务
        }

        /**
         * 并行流
         */
        public static class Flow {
            private String sequenceFlow;
            private String defaultFlow;
            private String conditionalFlow;
            private String messageFlow;
            private String link;
        }


    }


    public static class Gateways {
    }

    public static class Events {

    }

}
