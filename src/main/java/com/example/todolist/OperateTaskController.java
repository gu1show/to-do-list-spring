package com.example.todolist;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
     * @param request поля и значения, которые пришли в запросе.
     */
    @PostMapping("create-task")
    public void startProcessTaskCreating(@RequestBody final Map<String, Object> request) {
        long telegramUserId = ((Number) request.get("business key")).longValue();
        camundaStartService.startProcessTaskCreating(Long.toString(telegramUserId));
    }

    /**
     * Принимает запрос на установление заголовка задачи.
     * @param request поля и значения, которые пришли в запросе.
     */
    @PostMapping("change-title")
    public void setTaskTitle(@RequestBody final Map<String, Object> request) {
        long telegramUserId = ((Number) request.get("business key")).longValue();
        String taskTitle = (String) request.get("title");
        camundaStartService.setTaskTitle(Long.toString(telegramUserId), taskTitle);
    }

    /**
     * Принимает запрос на переход к действию, которое нужно будет выполнить.
     * @param request поля и значения, которые пришли в запросе.
     */
    @PostMapping("choose-action")
    public void setNextAction(@RequestBody final Map<String, Object> request) {
        long telegramUserId = ((Number) request.get("business key")).longValue();
        String action = (String) request.get("choice");
        camundaStartService.setNextAction(Long.toString(telegramUserId), action);
    }

    /**
     * Принимает запрос на установление описания задачи.
     * @param request поля и значения, которые пришли в запросе.
     */
    @PostMapping("change-description")
    public void setTaskDescription(@RequestBody final Map<String, Object> request) {
        long telegramUserId = ((Number) request.get("business key")).longValue();
        String taskDescription = (String) request.get("description");
        camundaStartService.setTaskDescription(Long.toString(telegramUserId), taskDescription);
    }

    /**
     * Принимает запрос на установление вложений задачи.
     * @param request поля и значения, которые пришли в запросе.
     */
    @PostMapping("change-attachment")
    public void setTaskAttachment(@RequestBody final Map<String, Object> request) {
        long telegramUserId = ((Number) request.get("business key")).longValue();
        String taskAttachment = (String) request.get("attachment");
        camundaStartService.setTaskAttachment(Long.toString(telegramUserId), taskAttachment);
    }

    /**
     * Принимает запрос на создание задачи или продолжение работы над ней.
     * @param request поля и значения, которые пришли в запросе.
     */
    @PostMapping("create-task-or-not")
    public void defineCreateTaskOrNot(@RequestBody final Map<String, Object> request) {
        long telegramUserId = ((Number) request.get("business key")).longValue();
        String answer = (String) request.get("choice");
        camundaStartService.defineCreateTaskOrNot(Long.toString(telegramUserId), answer);
    }
}
