class PowerOfTwoMaxHeap {
  constructor(numChildren) {
    if (numChildren <= 0) {
      throw new Error('Number of children must be positive');
    }
    this.heap = [];
    this.numChildren = numChildren;
  }

  insert(value) {
    this.heap.push(value);
    this.bubbleUp(this.heap.length - 1);
  }

  popMax() {
    if (this.isEmpty()) {
      throw new Error('Heap is empty');
    }

    const max = this.heap[0];
    const lastIdx = this.heap.length - 1;
    this.heap[0] = this.heap[lastIdx];
    this.heap.pop();
    this.bubbleDown(0);
    return max;
  }

  isEmpty() {
    return this.heap.length === 0;
  }

  bubbleUp(index) {
    if (index === 0) {
      return;
    }

    const parentIdx = Math.floor((index - 1) / this.numChildren);
    if (this.heap[index] > this.heap[parentIdx]) {
      [this.heap[index], this.heap[parentIdx]] = [this.heap[parentIdx], this.heap[index]];
      this.bubbleUp(parentIdx);
    }
  }

  bubbleDown(index) {
    let maxIdx = index;
    const startChildIdx = index * this.numChildren + 1;
    const endChildIdx = Math.min(startChildIdx + this.numChildren, this.heap.length);

    for (let i = startChildIdx; i < endChildIdx; i++) {
      if (this.heap[i] > this.heap[maxIdx]) {
        maxIdx = i;
      }
    }

    if (maxIdx !== index) {
      [this.heap[index], this.heap[maxIdx]] = [this.heap[maxIdx], this.heap[index]];
      this.bubbleDown(maxIdx);
    }
  }
}
