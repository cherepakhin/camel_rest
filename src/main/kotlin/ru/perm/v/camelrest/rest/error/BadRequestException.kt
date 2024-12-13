package ru.perm.v.camelrest.rest.error

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

// Answer with message
@ResponseStatus(HttpStatus.BAD_GATEWAY)
class BadRequestException(messageError: String?) : RuntimeException(messageError)