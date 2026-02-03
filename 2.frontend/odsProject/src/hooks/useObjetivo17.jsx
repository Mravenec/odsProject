import { useState, useEffect } from 'react';
import { objetivo17Service } from '../services/objetivo17Service';

export const useObjetivo17 = () => {
  const [data, setData] = useState({});
  const [loading, setLoading] = useState({});
  const [error, setError] = useState({});

  const fetchData = async (indicator) => {
    setLoading(prev => ({ ...prev, [indicator]: true }));
    setError(prev => ({ ...prev, [indicator]: null }));
    
    try {
      const methodName = `getIndicador_${indicator}`;
      const result = await objetivo17Service[methodName]();
      setData(prev => ({ ...prev, [indicator]: result }));
    } catch (err) {
      setError(prev => ({ ...prev, [indicator]: err.message }));
    } finally {
      setLoading(prev => ({ ...prev, [indicator]: false }));
    }
  };

  const fetchAllData = async () => {
    const indicators = [
      '17_1_1', '17_1_2', '17_2_1', '17_3_1', '17_3_2', '17_4_1',
      '17_5_1', '17_6_1', '17_7_1', '17_8_1', '17_9_1', '17_10_1',
      '17_11_1', '17_12_1', '17_13_1', '17_14_1', '17_15_1', '17_16_1',
      '17_17_1', '17_18_1', '17_18_2', '17_18_3', '17_19_1', '17_19_2'
    ];

    await Promise.all(indicators.map(indicator => fetchData(indicator)));
  };

  return {
    data,
    loading,
    error,
    fetchData,
    fetchAllData
  };
};
