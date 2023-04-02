package com.example.todolist;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstanceQuery;
import org.springframework.stereotype.Service;

/**
 * Класс, чтобы запустить процесс в Camunda.
 */
@Service
public class CamundaStartService {
    /**
     * Сервис Camunda, который даёт доступ к запуску процесса.
     */
    private final RuntimeService runtimeService;

    /**
     * Объект, с помощью которого выполняются запросы.
     */
    private final ProcessInstanceQuery processInstanceQuery;

    /**
     * Конструктор, в котором инициализируются экземпляры классов RuntimeService и ProcessInstanceQuery.
     */
    public CamundaStartService(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
        processInstanceQuery = runtimeService.createProcessInstanceQuery();
    }

    /**
     * Запускает процесс создания задачи в Camunda.
     * @param telegramUserId ID пользователя телеграмма, который является business key для процесса.
     */
    public void startProcessTaskCreating(String telegramUserId) {
        if (processInstanceQuery.processInstanceBusinessKey(telegramUserId).count() == 0) {
            runtimeService.startProcessInstanceByKey("creating-task", telegramUserId);
        }
    }
}
