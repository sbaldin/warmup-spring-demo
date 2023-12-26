package com.github.sbaldin.warmup.demospring.controller

import com.github.sbaldin.warmup.demospring.model.CreditScoring
import com.github.sbaldin.warmup.demospring.service.CreditScoringService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController()
@RequestMapping(
    "scoring/",
    consumes = [MediaType.APPLICATION_JSON_VALUE],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
class ScoringController(private val creditScoringService: CreditScoringService) {

    @PostMapping
    fun getCreditScore(@RequestBody request: CreditScoringRequest): CreditScoringResponse? {
        return CreditScoringResponse(
            scoringCheckPassed = creditScoringService.scoreCreditScoring(request.payload)
        )
    }

    @PostMapping("test",
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun test(@RequestBody request: CreditScoringRequest1): CreditScoringResponse? {
        return CreditScoringResponse(
            scoringCheckPassed = true
        )
    }
}

data class CreditScoringResponse(
    val scoringCheckPassed: Boolean,
)

data class CreditScoringRequest(
    val payload: CreditScoring,
)

data class CreditScoringRequest1(
    val payload: Int,
)
