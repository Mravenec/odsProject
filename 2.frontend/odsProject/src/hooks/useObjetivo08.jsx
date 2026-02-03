import { useState, useEffect } from 'react';
import { objetivo08Service } from '../services/objetivo08Service';

export const useObjetivo08 = () => {
  const [data, setData] = useState({});
  const [loading, setLoading] = useState({});
  const [error, setError] = useState({});

  const fetchData = async (indicator) => {
    setLoading(prev => ({ ...prev, [indicator]: true }));
    setError(prev => ({ ...prev, [indicator]: null }));
    
    try {
      const methodName = `getIndicador_${indicator}`;
      const result = await objetivo08Service[methodName]();
      setData(prev => ({ ...prev, [indicator]: result }));
    } catch (err) {
      setError(prev => ({ ...prev, [indicator]: err.message }));
    } finally {
      setLoading(prev => ({ ...prev, [indicator]: false }));
    }
  };

  const fetchAllData = async () => {
    const indicators = [
      '8_1_1', '8_2_1', '8_3_1', '8_4_1', '8_4_2', '8_5_1',
      '8_5_2', '8_6_1', '8_7_1', '8_8_1', '8_8_2', '8_9_1',
      '8_9_2', '8_10_1', '8_10_2', '8_a_1', '8_b_1'
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
