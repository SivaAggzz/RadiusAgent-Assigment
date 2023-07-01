Android Assignment

Role: Android Developer
Tasks :
a) From the API, display the list of facilities and its options by creating a UI, so that users
can select any one option from each facility. Display name and icon. For Example,
i) Property Type : Apartment, Condo, Boat House, Land - User can select only one
among this

b) Add the exclusion list by extending the above UI, so that users aren't able to select
from the exclusions combinations.
For example, users can’t select these options together.
Ex. 1 : Property Type : Land, Number of Rooms: 1 to 3 rooms.
Ex. 2 : Property Type : Boat House, Other Facilities : Garage

c) Use Core Data to Persist the data, so every time you load data from the DB, and refresh
your DB from API once a day.
API Url : https://my-json-server.typicode.com/iranjith4/ad-assignment/db
Type : GET
Icons : http://bit.ly/2KKsH0G

Expectations :
- Highly scalable code.
- Focus on code quality and architecture. MVP is preferred.
- Free to use any third party library, and justify your options.
Bonus Points :
- Simple and Reusable Components.
- Using rxJAVA, data binding, realm DB and retrofit.
For queries on the task contact darshana@radiusagent.com / mobile@radiusagent.com


Response:

{
  "facilities": [
    {
      "facility_id": "1",
      "name": "Property Type",
      "options": [
        {
          "name": "Apartment",
          "icon": "apartment",
          "id": "1"
        },
        {
          "name": "Condo",
          "icon": "condo",
          "id": "2"
        },
        {
          "name": "Boat House",
          "icon": "boat",
          "id": "3"
        },
        {
          "name": "Land",
          "icon": "land",
          "id": "4"
        }
      ]
    },
    {
      "facility_id": "2",
      "name": "Number of Rooms",
      "options": [
        {
          "name": "1 to 3 Rooms",
          "icon": "rooms",
          "id": "6"
        },
        {
          "name": "No Rooms",
          "icon": "no-room",
          "id": "7"
        }
      ]
    },
    {
      "facility_id": "3",
      "name": "Other facilities",
      "options": [
        {
          "name": "Swimming Pool",
          "icon": "swimming",
          "id": "10"
        },
        {
          "name": "Garden Area",
          "icon": "garden",
          "id": "11"
        },
        {
          "name": "Garage",
          "icon": "garage",
          "id": "12"
        }
      ]
    }
  ],
  "exclusions": [
    [
      {
        "facility_id": "1",
        "options_id": "4"
      },
      {
        "facility_id": "2",
        "options_id": "6"
      }
    ],
    [
      {
        "facility_id": "1",
        "options_id": "3"
      },
      {
        "facility_id": "3",
        "options_id": "12"
      }
    ],
    [
      {
        "facility_id": "2",
        "options_id": "7"
      },
      {
        "facility_id": "3",
        "options_id": "12"
      }
    ]
  ]
}
