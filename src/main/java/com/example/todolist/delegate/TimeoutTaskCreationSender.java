package com.example.todolist.delegate;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Named;

/**
 * Класс, чтобы сообщить о завершении процесса создания задачи по причине истечения времени.
 */
@Named
@Slf4j
public class TimeoutTaskCreationSender implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) {
        log.info("Процесс создания задачи завершён, потому что превышен лимит времени.");
    }
}
