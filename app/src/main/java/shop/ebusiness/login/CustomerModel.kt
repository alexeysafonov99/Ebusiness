package shop.ebusiness.login

class CustomerModel {
    //getter and setter
    var id = 0
    var datum: String? = null
    var eisengehalt = 0
    var cholesteringehalt = 0
    var blutzucker = 0
    var triglyceride = 0
    var blutdruck = 0
    var vitaminD = 0
    var vitaminB12 = 0

    constructor(
        id: Int,
        datum: String?,
        eisengehalt: Int,
        cholesteringehalt: Int,
        blutzucker: Int,
        triglyceride: Int,
        blutdruck: Int,
        vitaminD: Int,
        vitaminB12: Int
    ) {
        this.id = id
        this.datum = datum
        this.eisengehalt = eisengehalt
        this.cholesteringehalt = cholesteringehalt
        this.blutzucker = blutzucker
        this.triglyceride = triglyceride
        this.blutdruck = blutdruck
        this.vitaminD = vitaminD
        this.vitaminB12 = vitaminB12
    }

    constructor(
        customerID: Int,
        eisengehalt: Int,
        cholesteringehalt: Int,
        blutzucker: Int,
        triglyceride: Int,
        blutdruck: Int,
        vitaminD: Int,
        vitaminB12: Int
    )

    // toString is necessary for printing the contents of a class
    override fun toString(): String {
        return "CustomerModel{" +
                "id=" + id +
                ", datum='" + datum + '\'' +
                ", eisengehalt=" + eisengehalt +
                ", cholesteringehalt=" + cholesteringehalt +
                ", blutzucker=" + blutzucker +
                ", triglyceride=" + triglyceride +
                ", blutdruck=" + blutdruck +
                ", vitaminD=" + vitaminD +
                ", vitaminB12=" + vitaminB12 +
                '}'
    }
}