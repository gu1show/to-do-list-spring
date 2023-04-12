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
    public void execute(DelegateExecution execution) {
        log.info(String.format("Параметры задачи:\nЗаголовок: %s, описание: %s, вложения: %s.",
                execution.getVariable("title"),
                execution.getVariable("description"),
                execution.getVariable("attachment")));
    }
}
