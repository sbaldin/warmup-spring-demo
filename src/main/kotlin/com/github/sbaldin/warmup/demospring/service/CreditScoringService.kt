package com.github.sbaldin.warmup.demospring.service

import com.github.sbaldin.warmup.demospring.model.CreditScoring
import com.github.sbaldin.warmup.demospring.model.HealthScoring
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class CreditScoringService(
    val restTemplate: RestTemplate
) {

    fun scoreCreditScoring(creditScoring: CreditScoring): Boolean {
        // Perform credit scoring algorithms here

        val creditScore = creditScoring.creditScore
        val currentDebt = creditScoring.currentDebt
        val income = creditScoring.income

        val creditHistory = creditScoring.creditHistory
        val hasBadCreditHistory = creditHistory.any { entry -> entry.amount < 0 }

        val healthScoring = creditScoring.healthScoring
        val insuranceScore = calculateInsuranceScore(healthScoring)

        val carScoring = creditScoring.carScoring
        val previousAccidents = carScoring.previousAccidents

        //val houseScoring = creditScoring.houseScoring
        //val mortgageAmount = houseScoring.mortgageAmount

        // Perform additional CPU-intensive calculations
        val expensiveCalculation = performExpensiveCalculation()

        // Perform scoring calculations and make decisions

        val totalScore = creditScore + insuranceScore - previousAccidents - expensiveCalculation

        val isOpenCreditCard = totalScore > 500 && currentDebt / income < 0.4 && !hasBadCreditHistory

        return isOpenCreditCard
    }

    fun calculateInsuranceScore(healthScoring: HealthScoring): Int {
        // Perform insurance scoring algorithms here

        val medicalHistory = healthScoring.medicalHistory
        val hasSeriousMedicalCondition = medicalHistory.any { record -> record.condition == "serious" }

        val lifestyleScore = healthScoring.lifestyleScore

        // Perform additional CPU-intensive calculations for insurance scoring
        val expensiveInsuranceCalculation = performLongRunningRequests()

        val insuranceScore = lifestyleScore - if (hasSeriousMedicalCondition) 10 else 0 + expensiveInsuranceCalculation

        return insuranceScore
    }

    fun performExpensiveCalculation(): Double {
        // Perform expensive calculations that consume CPU resources

        fun fibonacci(n: Int): Int {
            return if (n <= 1) {
                n
            } else {
                fibonacci(n - 1) + fibonacci(n - 2)
            }
        }

        // Assume that calc 50th element is time-consuming operation needed in our scoring service
        return fibonacci(30) / 2.0
    }

    fun performLongRunningRequests(): Int {

        val yahooNewsUrl = "https://news.yahoo.com"
        val weatherServiceUrl = "https://openweathermap.org/city/1503901"
        val googleUrl = "https://google.com"

        val yahooNewsResponse = restTemplate.exchange(yahooNewsUrl, HttpMethod.GET, null, String::class.java).body?.length
            ?: 0
        val weatherServiceResponse = restTemplate.exchange(weatherServiceUrl, HttpMethod.GET, null, String::class.java).body?.length
            ?: 0
        val googleResponse = restTemplate.exchange(googleUrl, HttpMethod.GET, null, String::class.java).body?.length
            ?: 0

        return yahooNewsResponse + weatherServiceResponse + googleResponse
    }
}
