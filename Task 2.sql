             +--------------+
             |   DataPoint  |
             +--------------+
             | - data       |
             +--------------+

             ^
             |
             |
         +-----------------+
         |   ModeIdentifier |
         +-----------------+
         | - DUMP          |
         | - PASSTHROUGH   |
         | - VALIDATE      |
         +-----------------+

             ^
             |
             |
     +-----------------------+
     |  DatabaseIdentifier   |
     +-----------------------+
     | - POSTGRES            |
     | - REDIS               |
     | - ELASTIC             |
     +-----------------------+

             ^
             |
             |
      +----------------+
      |   DataProcessor |
      +----------------+
      | - mode: ModeIdentifier
      | - database: DatabaseIdentifier
      +----------------+
      | + configure(mode: ModeIdentifier, database: DatabaseIdentifier): void
      | + process(dataPoint: DataPoint): void
      +----------------+

     ^                 ^
     |                 |
     |                 |
+----------------+   +-------------------+   +----------------+
|    Postgres    |   |       Redis       |   |    Elastic     |
+----------------+   +-------------------+   +----------------+
|                |   |                   |   |                |
| - connect()    |   | - connect()       |   | - connect()    |
| - insert()     |   | - insert()        |   | - insert()     |
| - validate()   |   | - validate()      |   | - validate()   |
+----------------+   +-------------------+   +----------------+
