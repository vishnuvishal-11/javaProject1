package org.tech;

import lombok.NonNull;

public record Employee(@NonNull String id,@NonNull String name, String branch, String designation, String[] languages_known) {

}
