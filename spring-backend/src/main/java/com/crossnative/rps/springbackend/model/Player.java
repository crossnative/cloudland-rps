package com.crossnative.rps.springbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;
import java.util.UUID;
import lombok.Data;
import lombok.NonNull;

@Data
public class Player {

  @NonNull
  private UUID id;

  @NonNull
  private String name;

  @JsonIgnore
  private Integer choice;

  @JsonProperty
  public boolean hasChosen() {
    return Objects.nonNull(this.choice);
  }


}
