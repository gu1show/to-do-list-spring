package com.example.todolist.delegate;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Named;

/**
 * Класс, чтобы посылать приветствие пользователю.
 */
@Named
@Slf4j
public class GreetingSender implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        log.info("Send greeting.");
    }
}
