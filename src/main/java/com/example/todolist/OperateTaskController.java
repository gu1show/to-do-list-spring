package com.example.todolist;

import org.springframework.web.bind.annotation.*;

/**
 * Контроллер, который отправляет в CamundaService действие из запроса.
 */
@RestController
@RequestMapping("/api/")
public class OperateTaskController {
    /**
     * Сервис для взаимодействия с Camunda.
     */
    private final CamundaService camundaStartService;

    /**
     * Конструктор, в котором инициализируются экземпляр класса CamundaService.
     */
    public OperateTaskController(CamundaService camundaStartService) {
        this.camundaStartService = camundaStartService;
    }

    /**
     * Принимает запрос на запуск процесса создания задачи в Camunda.
     * @param telegramUserId ID пользователя Telegram, который является business key для процесса.
     */
    @PostMapping("create-task/{telegramUserId}")
    public void startProcessTaskCreating(@PathVariable final String telegramUserId) {
        camundaStartService.startProcessTaskCreating(telegramUserId);
    }

    /**
     * Принимает запрос на установление заголовка задачи.
     * @param telegramUserId ID пользователя Telegram, который является business key для процесса.
     * @param taskTitle заголовок задачи.
     */
    @PostMapping("task/{telegramUserId}/change-title/{taskTitle}")
    public void setTaskTitle(@PathVariable final String telegramUserId, @PathVariable final String taskTitle) {
        camundaStartService.setTaskTitle(telegramUserId, taskTitle);
    }

    /**
     * Принимает запрос на переход к действию, которое нужно будет выполнить.
     * @param telegramUserId ID пользователя Telegram, который является business key для процесса.
     * @param action действие, которое нужно делать далее.
     */
    @PostMapping("task/{telegramUserId}/choose-action/{action}")
    public void setNextAction(@PathVariable final String telegramUserId, @PathVariable final String action) {
        camundaStartService.setNextAction(telegramUserId, action);
    }

    /**
     * Принимает запрос на установление описания задачи.
     * @param telegramUserId ID пользователя Telegram, который является business key для процесса.
     * @param taskDescription описание задачи.
     */
    @PostMapping("task/{telegramUserId}/change-description/{taskDescription}")
    public void setTaskDescription(@PathVariable final String telegramUserId,
                                   @PathVariable final String taskDescription) {
        camundaStartService.setTaskDescription(telegramUserId, taskDescription);
    }

    /**
     * Принимает запрос на установление вложений задачи.
     * @param telegramUserId ID пользователя Telegram, который является business key для процесса.
     * @param taskAttachment вложение задачи.
     */
    @PostMapping("task/{telegramUserId}/change-attachment/{taskAttachment}")
    public void setTaskAttachment(@PathVariable final String telegramUserId,
                                  @PathVariable final String taskAttachment) {
        camundaStartService.setTaskAttachment(telegramUserId, taskAttachment);
    }

    /**
     * Принимает запрос на создание задачи или продолжение работы над ней.
     * @param telegramUserId ID пользователя Telegram, который является business key для процесса.
     * @param answer выбор, создавать ли задачу или нет.
     */
    @PostMapping("task/{telegramUserId}/create-task-or-not/{answer}")
    public void defineCreateTaskOrNot(@PathVariable final String telegramUserId,
                                      @PathVariable final String answer) {
        camundaStartService.defineCreateTaskOrNot(telegramUserId, answer);
    }
}
