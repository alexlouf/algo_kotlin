open class RingBuffer(count: Int){
    var array = arrayOfNulls<Any>(count)
    var readIndex = 0
    var writeIndex = 0

    open fun write(element: Any) : Boolean{
        return if (!isFull()) {
            array[writeIndex % array.size] = element
            writeIndex += 1
            true
        } else {
            false
        }
    }

    open fun read() : Any? {
        return if (!isEmpty()) {
            val element = array[readIndex % array.size]
            readIndex += 1
            element
        } else {
            null
        }
    }

    private fun availableSpaceForReading() : Int {
        return writeIndex - readIndex
    }

    private fun isEmpty(): Boolean {
        return availableSpaceForReading() == 0
    }

    private fun availableSpaceForWriting() : Int {
        return array.count() - availableSpaceForReading()
    }

    private fun isFull(): Boolean {
        return availableSpaceForWriting() == 0
    }

}

fun main() {
    var buffer = RingBuffer(5)

    buffer.write(123)
    buffer.write(456)
    buffer.write(789)
    buffer.write(666)

    println(buffer.read())
    println(buffer.read())
    println(buffer.read())

    buffer.write(333)
    buffer.write(555)

    println(buffer.read())
    println(buffer.read())
    println(buffer.read())
    println(buffer.read())

}