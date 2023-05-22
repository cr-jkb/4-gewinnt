object fieldDAO extends DAOInterface {

  val client: MongoClient = MongoClient()
  val database: MongoDatabase = client.getDatabase("mydb")
  val collection: MongoCollection[Document] =
    database.getCollection("savegames")

  def create(jsonField: String): Int = {
    val document: Document = Document("_id" -> 1, "x" -> 1)
    val query = collection.insertOne(jsonField)

    query
      .onComplete {
        println("finish"); 0
      }
      .onError { (e) =>
        println(s"$e"); 1
      }
  }

  def read(id: Int): String = {
    val document: Document = Document("_id" -> 1, "x" -> 1)
    val query = collection.get(id)

    query
      .onComplete {
        println("finish"); 0
      }
      .onError { (e) =>
        println(s"$e"); 1
      }
  }

  override def update(id: Int, jsonField: String): Unit = {
    val document: Document = Document("_id" -> 1, "x" -> 1)
    val query = collection.update(id, jsonField)

    query
      .onComplete {
        println("finish"); 0
      }
      .onError { (e) =>
        println(s"$e"); 1
      }
  }

  override def delete(id: Int): Unit = {
    val document: Document = Document("_id" -> 1, "x" -> 1)
    val query = collection.delete(id)

    query
      .onComplete {
        println("finish"); 0
      }
      .onError { (e) =>
        println(s"$e"); 1
      }
  }
}
