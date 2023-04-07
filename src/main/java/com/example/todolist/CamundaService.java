package com.example.todolist;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstanceQuery;
import org.springframework.stereotype.Service;

/**
 * Класс для взаимодействия с Camunda.
 */
@Service
@Slf4j
public class CamundaService {
    /**
     * Сервис, который предоставляет доступ к ProcessDefinition и ProcessInstance.
     */
    private final RuntimeService runtimeService;

    /**
     * Объект, с помощью которого выполняются запросы.
     */
    private final ProcessInstanceQuery processInstanceQuery;

    /**
     * Конструктор, в котором инициализируются экземпляры классов RuntimeService и ProcessInstanceQuery.
     */
    public CamundaService(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
        processInstanceQuery = runtimeService.createProcessInstanceQuery();
    }

    /**
     * Запускает процесс создания задачи в Camunda.
     * @param telegramUserId ID пользователя Telegram, который является business key для процесса.
     */
    public void startProcessTaskCreating(final String telegramUserId) {
        if (processInstanceQuery.processInstanceBusinessKey(telegramUserId).count() == 0) {
            runtimeService.startProcessInstanceByKey("creating-task", telegramUserId);
        }
    }

    /**
     * Устанавливает заголовок у задачи.
     * @param telegramUserId ID пользователя Telegram, который является business key для процесса.
     * @param taskTitle заголовок задачи.
     */
    public void setTaskTitle(final String telegramUserId, final String taskTitle) {
        if (processInstanceQuery.processInstanceBusinessKey(telegramUserId).count() < 3) {
            runtimeService.createMessageCorrelation("MessageChangeTitle")
                    .processInstanceBusinessKey(telegramUserId)
                    .setVariable("title", taskTitle)
                    .correlate();

            log.info(String.format("Заголовок: %s.", taskTitle));
        }

        log.info("""
                Что делать далее?
                Добавить описание.
                Прикрепить вложения.
                Изменить заголовок.
                Посмотреть/добавить составленную задачу.
                Отменить процесс создания задачи.""");
    }

    /**
     * Устанавливает дальнейший путь процесса.
     * @param telegramUserId ID пользователя Telegram, который является business key для процесса.
     * @param action инструкция, что делать далее.
     */
    public void setNextAction(final String telegramUserId, final String action) {
        if (processInstanceQuery.processInstanceBusinessKey(telegramUserId).count() == 2) {
            log.info(String.format("Вы выбрали %s.", action));

            runtimeService.createMessageCorrelation("MessageChooseAction")
                    .processInstanceBusinessKey(telegramUserId)
                    .setVariable("choice", action)
                    .correlate();
        }
    }

    /**
     * Устанавливает описание задачи.
     * @param telegramUserId ID пользователя Telegram, который является business key для процесса.
     * @param taskDescription описание задачи.
     */
    public void setTaskDescription(final String telegramUserId, final String taskDescription) {
        if (processInstanceQuery.processInstanceBusinessKey(telegramUserId).count() == 2) {
            runtimeService.createMessageCorrelation("MessageInputDescription")
                    .processInstanceBusinessKey(telegramUserId)
                    .setVariable("description", taskDescription)
                    .correlate();

            log.info(String.format("Описание: %s.", taskDescription));
        }
    }

    /**
     * Устанавливает вложение задачи.
     * @param telegramUserId ID пользователя Telegram, который является business key для процесса.
     * @param taskAttachment вложение задачи.
     */
    public void setTaskAttachment(final String telegramUserId, final String taskAttachment) {
        if (processInstanceQuery.processInstanceBusinessKey(telegramUserId).count() == 2) {
            runtimeService.createMessageCorrelation("MessageAddAttachment")
                    .processInstanceBusinessKey(telegramUserId)
                    .setVariable("attachment", taskAttachment)
                    .correlate();

            log.info(String.format("Вложения: %s.", taskAttachment));
        }
    }

    /**
     * Определяет, создавать ли задачу или нет.
     * @param telegramUserId ID пользователя Telegram, который является business key для процесса.
     * @param choice выбор, создавать ли задачу или нет.
     */
    public void defineCreateTaskOrNot(final String telegramUserId, final String choice) {
        if (processInstanceQuery.processInstanceBusinessKey(telegramUserId).count() == 1) {
            log.info(String.format("Вы выбрали %s.", choice));

            runtimeService.createMessageCorrelation("MessageCreateTaskOrNot")
                    .processInstanceBusinessKey(telegramUserId)
                    .setVariable("choice", choice)
                    .correlate();
        }
    }
}
