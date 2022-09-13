package dev.the.mag.exchangebrokerbackend.converters

import java.sql.Date
import java.time.LocalDate
import javax.persistence.AttributeConverter
import javax.persistence.Converter

@Converter(autoApply = true)
class LocalDateAttributeConverter : AttributeConverter<LocalDate, Date> {
    override fun convertToDatabaseColumn(attribute: LocalDate?): Date? {
        return if (attribute == null) null else Date.valueOf(attribute)
    }

    override fun convertToEntityAttribute(dbData: Date?): LocalDate? {
        return dbData?.toLocalDate()
    }
}
