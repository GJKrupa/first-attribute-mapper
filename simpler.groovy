import groovy.json.JsonBuilder

def xml = new XmlSlurper().parseText('''
<person>
    <identities>
        <identity>
            <lastName>Smith</lastName>
        </identity>
        <identity>
            <firstName>Mary</firstName>
            <lastName>Jones</lastName>
        </identity>
        <identity>
            <dob>01/01/2001</dob>
        </identity>
        <gender>F</gender>
    </identities>
</person>
''')

def builder = new JsonBuilder()

people = [
        person: xml.identities.identity.inject([:]) { a, b ->
            [
                    firstName: a.firstName ?: b.firstName.text(),
                    lastName: a.lastName ?: b.lastName.text(),
                    dob: a.dob ?: b.dob.text(),
            ]
        } + [
                gender: xml.identities.gender.text(),
                isFlight: true
        ]

]

builder.people(people)
println builder.toPrettyString()
