package com.example.todolist.delegate;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Named;

/**
 * Класс, чтобы посылать всю информацию о задаче.
 */
@Named
@Slf4j
public class AllTaskInformationSender implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        log.info(String.format("Title: %s, description: %s, attachment: %s.",
                execution.getVariable("taskTitle"),
                execution.getVariable("description"),
                execution.getVariable("attachment")));
    }
}
