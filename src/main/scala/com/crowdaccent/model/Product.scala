package com.crowdaccent {
package model {

import net.liftweb.mapper._

class Product extends LongKeyedMapper[Product] {
  def getSingleton = Product
  def primaryKeyField = id
  object id extends MappedLongIndex(this)
  object subject extends MappedString(this, 200)
  object summary extends MappedText(this)
  object imageURL extends MappedString(this, 512)
  object Category extends MappedString(this, 512)
  object date_created extends MappedDateTime(this)
}

object Product extends Product with LongKeyedMetaMapper[Product]

}
}