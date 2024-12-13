package ru.perm.v.camelrest.rest.error

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.BAD_GATEWAY)
class BadRequestException(message: String?) : RuntimeException(message) {
}