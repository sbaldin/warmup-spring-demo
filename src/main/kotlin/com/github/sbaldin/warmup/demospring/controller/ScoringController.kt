package com.github.sbaldin.warmup.demospring.controller

import com.github.sbaldin.warmup.demospring.model.CarScoring
import com.github.sbaldin.warmup.demospring.model.CreditHistoryEntry
import com.github.sbaldin.warmup.demospring.model.CreditScoring
import com.github.sbaldin.warmup.demospring.model.HealthScoring
import com.github.sbaldin.warmup.demospring.model.HouseScoring
import com.github.sbaldin.warmup.demospring.model.InsuranceBroker
import com.github.sbaldin.warmup.demospring.model.MedicalRecord
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

    @PostMapping("no-json-parsing")
    fun getCreditScoreNoJsonParsing(): CreditScoringResponse? {

        return CreditScoringResponse(
            scoringCheckPassed = creditScoringService.scoreCreditScoring(
                CreditScoring(
                    clientId = "1",
                    clientName = "Client",
                    creditScore = 75,
                    creditHistory = listOf(CreditHistoryEntry(
                        "1.11.2001",
                        "Test Entry",
                        65.0
                    )),
                    currentDebt = 20000.0,
                    income = 1000.0,
                    healthScoring = HealthScoring(
                        medicalHistory = listOf(
                            MedicalRecord(
                                "",
                                "",
                                ""
                            )),
                        lifestyleScore = 10,
                        insuranceScore = 75,

                        ),
                    carScoring = CarScoring(
                        carMake = "audi",
                        carModel = "a4",
                        year = 1999,
                        previousAccidents = 10,
                        mileage = 5.0,
                    ),
                    houseScoring = HouseScoring(
                        propertyType = "house",
                        address = "address",
                        yearBuilt = 2000,
                        numberOfBedrooms = 5,
                        mortgageAmount = 12.0,
                    ),
                    insuranceBroker = InsuranceBroker(
                        brokerId = "1",
                        brokerName = "broker-1",
                        brokerLicense = "1",
                        brokerAddress = "avenue street 1",
                        brokerPhoneNumber = "123-123",
                        brokerEmail = "broker@email.com",
                        brokerSpecialization = "mortgage",
                        issuePaper = null,
                    ),
                    insuranceCarrier = null,
                )
            )
        )
    }
}

data class CreditScoringResponse(
    val scoringCheckPassed: Boolean,
)

data class CreditScoringRequest(
    val payload: CreditScoring,
)
