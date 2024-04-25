import { useState ,useEffect, useCallback} from "react";

async function sendHttpRequest(url,config){
    const res = await fetch(url,config)

    const resData = res.json()
    if(!res.ok){
        throw new Error(resData.message ||'Something went wrong, failed to send request.');
    }
    return resData
}

export default function useHttp(url,config, initialData){
    const [data, setData] = useState(initialData);
    const [isLoading,setIsLoading] = useState(false);
    const [error, setError] = useState();


  const sendRequest = useCallback (async function sendRequest(data){
        setIsLoading(true)
        try {
            const resData = await sendHttpRequest(url,{...config,body:data});
            setData(resData)            
        } catch (error) {
            setError(error.message || 'Something went Wrong')            
        }
        setIsLoading(false)
    },[url,config])

    useEffect(() =>{
        if(config &&(config.method === 'GET' ||!config.method) || !config){
            sendRequest();
        }
    },[sendRequest,config])
    return{
        data,
        isLoading,
        error,
        sendRequest
    }

}