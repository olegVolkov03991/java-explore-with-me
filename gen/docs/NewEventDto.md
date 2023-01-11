

# NewEventDto

Новое событие
## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**annotation** | **String** | Краткое описание события | 
**category** | **Long** | id категории к которой относится событие | 
**description** | **String** | Полное описание события | 
**eventDate** | **String** | Дата и время на которые намечено событие. Дата и время указываются в формате \&quot;yyyy-MM-dd HH:mm:ss\&quot; | 
**location** | [**Location**](Location.md) |  | 
**paid** | **Boolean** | Нужно ли оплачивать участие в событии |  [optional]
**participantLimit** | **Integer** | Ограничение на количество участников. Значение 0 - означает отсутствие ограничения |  [optional]
**requestModeration** | **Boolean** | Нужна ли пре-модерация заявок на участие. Если true, то все заявки будут ожидать подтверждения инициатором события. Если false - то будут подтверждаться автоматически. |  [optional]
**title** | **String** | Заголовок события | 



