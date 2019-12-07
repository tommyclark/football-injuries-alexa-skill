package com.footballinjuries.service

import com.footballinjuries.POJO.Footballer
import spock.lang.Specification

class ResponseObjectMapperSpec extends Specification {

    String json = this.getClass().getResource('/api_response.json').text

    def "Map json to an object"() {
        given: "A mapper"
        ResponseObjectMapper responseObjectMapper = new ResponseObjectMapper()

        when: "The mapper is called"
        Footballer footballer = responseObjectMapper.map(json)

        then: "The footballer is what is expected from the supplied json"
        footballer.id == 300
        footballer.name == "G. Wijnaldum"
        footballer.firstname == "Georginio"
        footballer.lastname == "Wijnaldum"
        !footballer.injured
    }
}
