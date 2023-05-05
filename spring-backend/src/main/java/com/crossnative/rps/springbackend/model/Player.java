package com.crossnative.rps.springbackend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import java.util.Objects;
import java.util.UUID;
import lombok.Data;

@Data
public class Player {

  @JsonProperty(access = Access.WRITE_ONLY)
  private UUID id;

  private String name;

  @JsonProperty(access = Access.WRITE_ONLY)
  private String choice;

  @JsonProperty
  public boolean hasChosen() {
    return Objects.nonNull(this.choice);
  }

  public void setChoice(final String choice) {
    if (Objects.nonNull(this.choice)) {
      throw new IllegalArgumentException("Player " + this.name + " already made a choice!");
    }
    this.choice = choice.toUpperCase();
  }


}
