package ru.perm.v.camelrest.service.impl

import org.springframework.stereotype.Service
import ru.perm.v.camelrest.service.ConvertorService

@Service
class ConvertorServiceImpl : ConvertorService {

    override fun echo(text: String): String {
        return text
    }
}