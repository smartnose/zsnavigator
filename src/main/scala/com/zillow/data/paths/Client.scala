package com.zillow.data.paths

/**
  * @author weil
  */
trait Client {
    def hasObject(fullPath: String): Boolean
    def enumerateByPrefix(prefix: String): Iterable[String]
}
