package com.permata.appraisal2.process;

public class TaskExecutorSend {

    private org.springframework.core.task.TaskExecutor taskExecutor;

    public TaskExecutorSend(org.springframework.core.task.TaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }
}