package com.zillow.data.paths

import shapeless.{Id, LabelledGeneric, MkLabelledGenericLens, Poly1}
import shapeless.poly._

/**
  * @author weil
  */
trait Navigator[T] {
    def prefixOf(data: T): String
}

case class Column[V](v: V) {
    def value = v
}

object wrap extends Poly1 {
    implicit def caseString = at[String](x => Column(x))
    implicit def caseInt = at[Int](x => Column(x))

}

object wrap2 extends (Id ~> Column) {
    def apply[T](s: Id[T]) = Column(s)
}

object NavigatorMain {
    def of[T](client: Client): Navigator[T] = ???

    case class Person(name: String, age: Int)
    def main(args: Array[String]): Unit = {
        import shapeless.record._
        import shapeless.syntax.singleton._
        val gen = LabelledGeneric[Person]
        val p = Person("kk",20)
        val list = gen.to(p)
        list('name)
        val mapped = list map wrap2
        print(mapped)
        val zipped = mapped.zipWithKeys(list.keys)
        print(zipped('name).value)
        val k = 'name
        print(k)

    }
}