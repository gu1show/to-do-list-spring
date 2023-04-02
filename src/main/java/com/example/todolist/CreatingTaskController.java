package com.example.todolist;

import org.springframework.web.bind.annotation.*;

/**
 * Контроллер, который запускает процесс создания задачи в Camunda, получая запрос.
 */
@RestController
@RequestMapping("/api/")
public class CreatingTaskController {
    /**
     * Сервис для запуска процесса в Camunda.
     */
    private final CamundaStartService camundaStartService;

    /**
     * Конструктор, в котором инициализируются экземпляр класса CamundaStartService.
     */
    public CreatingTaskController(CamundaStartService camundaStartService) {
        this.camundaStartService = camundaStartService;
    }

    /**
     * Запускает процесс создания задачи в Camunda.
     * @param telegramUserId ID пользователя телеграмма, который является business key для процесса.
     */
    @PostMapping("create-task/{telegramUserId}")
    public void startProcessTaskCreating(@PathVariable String telegramUserId) {
        camundaStartService.startProcessTaskCreating(telegramUserId);
    }
}
