/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.cloudml.zen.ml.sampler

import java.util.Random

import spire.math.{Numeric => spNum}


trait Sampler[@specialized(Double, Int, Float, Long) T] {
  protected def numer: spNum[T]
  def apply(state: Int): T
  def norm: T
  def sampleFrom(base: T, gen: Random): Int

  def applyDouble(state: Int): Double = numer.toDouble(apply(state))

  def normDouble: Double = numer.toDouble(norm)

  def sampleFromDouble(base: Double, gen: Random): Int = sampleFrom(numer.fromDouble(base), gen)

  def sampleRandom(gen: Random): Int = {
    val u = gen.nextDouble() * normDouble
    sampleFromDouble(u, gen)
  }
}
