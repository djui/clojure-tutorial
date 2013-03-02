(ns foo.core
  (:gen-class))
;; My first test application
;; Run print loop and download concurrently

(defn tick [counter & args]
  (println (str "My state is: " counter " with args: " args))
  (when (zero? (rem counter 5)) (/ 0 0))
  (inc counter))

(defn error-fun [a e]
  (println (str "I crashed! Because of " e))
  (Thread/sleep 1000))

(defn -main [& args]
  (let [tick-agent (agent 1 :error-handler error-fun)
        crawl-agent (agent nil)]
    (loop [i 0]
      (send-off tick-agent tick i args)
      (recur (inc i))))
  
  (println "done"))
