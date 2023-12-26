package com.github.sbaldin.warmup.demospring.model

data class CreditScoring(
    val clientId: String,
    val clientName: String,
    val creditScore: Int,
    val creditHistory: List<CreditHistoryEntry>,
    val currentDebt: Double,
    val income: Double,
    val healthScoring: HealthScoring,
    val carScoring: CarScoring,
    val houseScoring: HouseScoring,
    val insuranceBroker: InsuranceBroker,
    val insuranceCarrier: InsuranceCarrier?
)

data class CreditHistoryEntry(
    val date: String,
    val description: String,
    val amount: Double
)

data class HealthScoring(
    val medicalHistory: List<MedicalRecord>,
    val lifestyleScore: Int,
    val insuranceScore: Int
)

data class MedicalRecord(
    val condition: String,
    val diagnosisDate: String,
    val prescription: String
)

data class CarScoring(
    val carMake: String,
    val carModel: String,
    val year: Int,
    val previousAccidents: Int,
    val mileage: Double
)

data class HouseScoring(
    val propertyType: String,
    val address: String,
    val yearBuilt: Int,
    val numberOfBedrooms: Int,
    val mortgageAmount: Double
)

data class InsuranceBroker(
    val brokerId: String,
    val brokerName: String,
    val brokerLicense: String,
    val brokerAddress: String,
    val brokerPhoneNumber: String,
    val brokerEmail: String,
    val brokerSpecialization: String,
    val issuePaper: IssuePaper?
)

data class InsuranceCarrier(
    val carrierId: String,
    val carrierName: String,
    val carrierAddress: String,
    val carrierPhoneNumber: String,
    val carrierEmail: String,
    val carrierRating: Int,
    val carrierServices: List<String>
)

data class IssuePaper(
    val policy: Policy?,
    val startDate: String,
    val endDate: String    
)

data class Policy(
    val policyNumber: String,
    val premium: Double,
    val fee: Double,
    val revenue: Double,
    val startDate: String,
    val endDate: String,
    val year: Int,
    val clientId: String
)
