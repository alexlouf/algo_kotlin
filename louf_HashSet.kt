open class HashSet<T> {

    val dictionnary = mutableMapOf<T, Boolean?>()

    open fun insert(v: T) {
        dictionnary[v] = true
    }

    open fun remove(v: T) {
        dictionnary[v] = null
    }

    open fun contains(v: T) : Boolean? {
        return dictionnary[v]
    }

    open fun allElements() : List<MutableSet<T>> {
        return listOf(dictionnary.keys)
    }

    open fun union(otherSet: HashSet<T>) : HashSet<T> {
        var combined = HashSet<T>()

        for (obj in this.dictionnary.keys) {
            combined.insert(obj)
        }
        for (obj in otherSet.dictionnary.keys) {
            combined.insert(obj)
        }

        return combined
    }

    open fun intersect( otherSet: HashSet<T>) : HashSet<T> {
        var common = HashSet<T>()

        for (obj in this.dictionnary.keys) {
            if (otherSet.contains(obj) == true) {
                common.insert(obj)
            }
        }
        return common
    }

    open fun difference( otherSet: HashSet<T>) : HashSet<T> {
        var diff = HashSet<T>()

        for (obj in this.dictionnary.keys) {
            if (otherSet.contains(obj) == null) {
                diff.insert(obj)
            }
        }
        return diff
    }

    open var count: Int = dictionnary.count()

    open var isEmpty: Boolean = dictionnary.isEmpty()
}

fun main() {
    var set = HashSet<String>()

    println("Test insertions multiples :")

    set.insert("one")
    set.insert("two")
    set.insert("three")
    println(set.allElements())

    set.insert("two")
    println(set.allElements())

    println(set.contains("one"))
    set.remove("one")
    println(set.contains("one"))

    println("Test union de tableau :")
    println("Tableau a :")

    var setA = HashSet<Int>()
    setA.insert(1)
    setA.insert(2)
    setA.insert(3)
    setA.insert(4)
    println(setA.allElements())

    println("Tableau b :")
    var setB = HashSet<Int>()
    setB.insert(3)
    setB.insert(4)
    setB.insert(5)
    setB.insert(6)
    println(setB.allElements())

    println("résultat fusion :")
    val union = setA.union(setB)
    println(union.allElements())

    println("Intersection de deux tableaux :")

    val intersection = setA.intersect(setB)
    println(intersection.allElements())

    println("Différence entre a et b de deux points de vue :")

    val difference1 = setA.difference(setB)
    println(difference1.allElements())          // [2, 1]

    val difference2 = setB.difference(setA)
    println(difference2.allElements())

}