package com.example.todolist.delegate;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Named;

/**
 * Класс, чтобы послать вопрос о создании задачи пользователю.
 */
@Named
@Slf4j
public class CreateTaskOrNotSender implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) {
        log.info("Создать задачу?");
    }
}
