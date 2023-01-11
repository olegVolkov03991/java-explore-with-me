/*
 * Main service API
 * No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
 *
 * The version of the OpenAPI document: 1.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package org.openapitools.client.model;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import org.openapitools.client.model.Location;

/**
 * Новое событие
 */
@ApiModel(description = "Новое событие")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2023-01-11T04:43:55.397025100+03:00[Europe/Moscow]")
public class NewEventDto {
  public static final String SERIALIZED_NAME_ANNOTATION = "annotation";
  @SerializedName(SERIALIZED_NAME_ANNOTATION)
  private String annotation;

  public static final String SERIALIZED_NAME_CATEGORY = "category";
  @SerializedName(SERIALIZED_NAME_CATEGORY)
  private Long category;

  public static final String SERIALIZED_NAME_DESCRIPTION = "description";
  @SerializedName(SERIALIZED_NAME_DESCRIPTION)
  private String description;

  public static final String SERIALIZED_NAME_EVENT_DATE = "eventDate";
  @SerializedName(SERIALIZED_NAME_EVENT_DATE)
  private String eventDate;

  public static final String SERIALIZED_NAME_LOCATION = "location";
  @SerializedName(SERIALIZED_NAME_LOCATION)
  private Location location;

  public static final String SERIALIZED_NAME_PAID = "paid";
  @SerializedName(SERIALIZED_NAME_PAID)
  private Boolean paid = false;

  public static final String SERIALIZED_NAME_PARTICIPANT_LIMIT = "participantLimit";
  @SerializedName(SERIALIZED_NAME_PARTICIPANT_LIMIT)
  private Integer participantLimit = 0;

  public static final String SERIALIZED_NAME_REQUEST_MODERATION = "requestModeration";
  @SerializedName(SERIALIZED_NAME_REQUEST_MODERATION)
  private Boolean requestModeration = true;

  public static final String SERIALIZED_NAME_TITLE = "title";
  @SerializedName(SERIALIZED_NAME_TITLE)
  private String title;


  public NewEventDto annotation(String annotation) {
    
    this.annotation = annotation;
    return this;
  }

   /**
   * Краткое описание события
   * @return annotation
  **/
  @ApiModelProperty(example = "Сплав на байдарках похож на полет.", required = true, value = "Краткое описание события")

  public String getAnnotation() {
    return annotation;
  }


  public void setAnnotation(String annotation) {
    this.annotation = annotation;
  }


  public NewEventDto category(Long category) {
    
    this.category = category;
    return this;
  }

   /**
   * id категории к которой относится событие
   * @return category
  **/
  @ApiModelProperty(example = "2", required = true, value = "id категории к которой относится событие")

  public Long getCategory() {
    return category;
  }


  public void setCategory(Long category) {
    this.category = category;
  }


  public NewEventDto description(String description) {
    
    this.description = description;
    return this;
  }

   /**
   * Полное описание события
   * @return description
  **/
  @ApiModelProperty(example = "Сплав на байдарках похож на полет. На спокойной воде — это парение. На бурной, порожистой — выполнение фигур высшего пилотажа. И то, и другое дарят чувство обновления, феерические эмоции, яркие впечатления.", required = true, value = "Полное описание события")

  public String getDescription() {
    return description;
  }


  public void setDescription(String description) {
    this.description = description;
  }


  public NewEventDto eventDate(String eventDate) {
    
    this.eventDate = eventDate;
    return this;
  }

   /**
   * Дата и время на которые намечено событие. Дата и время указываются в формате \&quot;yyyy-MM-dd HH:mm:ss\&quot;
   * @return eventDate
  **/
  @ApiModelProperty(example = "2024-12-31 15:10:05", required = true, value = "Дата и время на которые намечено событие. Дата и время указываются в формате \"yyyy-MM-dd HH:mm:ss\"")

  public String getEventDate() {
    return eventDate;
  }


  public void setEventDate(String eventDate) {
    this.eventDate = eventDate;
  }


  public NewEventDto location(Location location) {
    
    this.location = location;
    return this;
  }

   /**
   * Get location
   * @return location
  **/
  @ApiModelProperty(required = true, value = "")

  public Location getLocation() {
    return location;
  }


  public void setLocation(Location location) {
    this.location = location;
  }


  public NewEventDto paid(Boolean paid) {
    
    this.paid = paid;
    return this;
  }

   /**
   * Нужно ли оплачивать участие в событии
   * @return paid
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "true", value = "Нужно ли оплачивать участие в событии")

  public Boolean getPaid() {
    return paid;
  }


  public void setPaid(Boolean paid) {
    this.paid = paid;
  }


  public NewEventDto participantLimit(Integer participantLimit) {
    
    this.participantLimit = participantLimit;
    return this;
  }

   /**
   * Ограничение на количество участников. Значение 0 - означает отсутствие ограничения
   * @return participantLimit
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "10", value = "Ограничение на количество участников. Значение 0 - означает отсутствие ограничения")

  public Integer getParticipantLimit() {
    return participantLimit;
  }


  public void setParticipantLimit(Integer participantLimit) {
    this.participantLimit = participantLimit;
  }


  public NewEventDto requestModeration(Boolean requestModeration) {
    
    this.requestModeration = requestModeration;
    return this;
  }

   /**
   * Нужна ли пре-модерация заявок на участие. Если true, то все заявки будут ожидать подтверждения инициатором события. Если false - то будут подтверждаться автоматически.
   * @return requestModeration
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "false", value = "Нужна ли пре-модерация заявок на участие. Если true, то все заявки будут ожидать подтверждения инициатором события. Если false - то будут подтверждаться автоматически.")

  public Boolean getRequestModeration() {
    return requestModeration;
  }


  public void setRequestModeration(Boolean requestModeration) {
    this.requestModeration = requestModeration;
  }


  public NewEventDto title(String title) {
    
    this.title = title;
    return this;
  }

   /**
   * Заголовок события
   * @return title
  **/
  @ApiModelProperty(example = "Сплав на байдарках", required = true, value = "Заголовок события")

  public String getTitle() {
    return title;
  }


  public void setTitle(String title) {
    this.title = title;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NewEventDto newEventDto = (NewEventDto) o;
    return Objects.equals(this.annotation, newEventDto.annotation) &&
        Objects.equals(this.category, newEventDto.category) &&
        Objects.equals(this.description, newEventDto.description) &&
        Objects.equals(this.eventDate, newEventDto.eventDate) &&
        Objects.equals(this.location, newEventDto.location) &&
        Objects.equals(this.paid, newEventDto.paid) &&
        Objects.equals(this.participantLimit, newEventDto.participantLimit) &&
        Objects.equals(this.requestModeration, newEventDto.requestModeration) &&
        Objects.equals(this.title, newEventDto.title);
  }

  @Override
  public int hashCode() {
    return Objects.hash(annotation, category, description, eventDate, location, paid, participantLimit, requestModeration, title);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NewEventDto {\n");
    sb.append("    annotation: ").append(toIndentedString(annotation)).append("\n");
    sb.append("    category: ").append(toIndentedString(category)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    eventDate: ").append(toIndentedString(eventDate)).append("\n");
    sb.append("    location: ").append(toIndentedString(location)).append("\n");
    sb.append("    paid: ").append(toIndentedString(paid)).append("\n");
    sb.append("    participantLimit: ").append(toIndentedString(participantLimit)).append("\n");
    sb.append("    requestModeration: ").append(toIndentedString(requestModeration)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

